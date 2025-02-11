/*
 * Copyright Consensys Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package net.consensys.linea.sequencer.txselection.selectors;

import static net.consensys.linea.sequencer.txselection.LineaTransactionSelectionResult.BLOCK_CALLDATA_OVERFLOW;
import static org.hyperledger.besu.plugin.data.TransactionSelectionResult.SELECTED;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.besu.datatypes.PendingTransaction;
import org.hyperledger.besu.datatypes.Transaction;
import org.hyperledger.besu.plugin.data.TransactionProcessingResult;
import org.hyperledger.besu.plugin.data.TransactionSelectionResult;
import org.hyperledger.besu.plugin.services.txselection.PluginTransactionSelector;

/**
 * This class implements TransactionSelector and provides a specific implementation for evaluating
 * transactions based on the size of the call data. It checks if adding a transaction to the block
 * pushes the call data size of the block over the limit.
 */
@Slf4j
@RequiredArgsConstructor
public class MaxBlockCallDataTransactionSelector implements PluginTransactionSelector {

  private final int maxBlockCallDataSize;
  private int cumulativeBlockCallDataSize;

  /**
   * Evaluates a transaction before processing. Checks if adding the transaction to the block pushes
   * the call data size of the block over the limit.
   *
   * @param pendingTransaction The transaction to evaluate.
   * @return BLOCK_CALLDATA_FULL if the call data size of a transactions pushes the size for the
   *     block over the limit, otherwise SELECTED.
   */
  @Override
  public TransactionSelectionResult evaluateTransactionPreProcessing(
      final PendingTransaction pendingTransaction) {

    final Transaction transaction = pendingTransaction.getTransaction();
    final int transactionCallDataSize = transaction.getPayload().size();

    if (isTransactionExceedingBlockCallDataSizeLimit(transactionCallDataSize)) {
      log.atTrace()
          .setMessage(
              "Cumulative block calldata size including tx {} is {} greater than the max allowed {}, skipping tx")
          .addArgument(pendingTransaction.getTransaction()::getHash)
          .addArgument(() -> cumulativeBlockCallDataSize + transactionCallDataSize)
          .addArgument(maxBlockCallDataSize)
          .log();
      return BLOCK_CALLDATA_OVERFLOW;
    }

    log.atTrace()
        .setMessage("Cumulative block calldata size including tx {} is {}")
        .addArgument(pendingTransaction.getTransaction()::getHash)
        .addArgument(cumulativeBlockCallDataSize)
        .log();

    return SELECTED;
  }

  /**
   * Checks if the total call data size of all transactions in a block would exceed the maximum
   * allowed size if the given transaction were added.
   *
   * @param transactionCallDataSize The call data size of the transaction.
   * @return true if the total call data size would be too big, false otherwise.
   */
  private boolean isTransactionExceedingBlockCallDataSizeLimit(int transactionCallDataSize) {
    return Math.addExact(cumulativeBlockCallDataSize, transactionCallDataSize)
        > maxBlockCallDataSize;
  }

  /**
   * Updates the total call data size of all transactions in a block when a transaction is selected.
   *
   * @param pendingTransaction The selected transaction.
   */
  @Override
  public void onTransactionSelected(
      final PendingTransaction pendingTransaction,
      final TransactionProcessingResult transactionProcessingResult) {
    final int transactionCallDataSize = pendingTransaction.getTransaction().getPayload().size();
    cumulativeBlockCallDataSize =
        Math.addExact(cumulativeBlockCallDataSize, transactionCallDataSize);
  }

  /**
   * No evaluation is performed post-processing.
   *
   * @param pendingTransaction The processed transaction.
   * @param processingResult The result of the transaction processing.
   * @return Always returns SELECTED.
   */
  @Override
  public TransactionSelectionResult evaluateTransactionPostProcessing(
      final PendingTransaction pendingTransaction,
      final TransactionProcessingResult processingResult) {
    // Evaluation done in pre-processing, no action needed here.
    return SELECTED;
  }
}
