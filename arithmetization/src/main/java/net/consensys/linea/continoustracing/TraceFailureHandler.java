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
package net.consensys.linea.continoustracing;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;
import net.consensys.linea.continoustracing.exception.InvalidTraceHandlerException;
import net.consensys.linea.corset.CorsetValidator;
import org.apache.commons.io.FileUtils;
import org.hyperledger.besu.datatypes.Hash;
import org.hyperledger.besu.plugin.data.BlockHeader;

@Slf4j
public class TraceFailureHandler {
  final SlackNotificationService slackNotificationService;
  static final File INVALID_TRACE_DIRECTORY =
      new File(FileUtils.getUserDirectory(), "invalid-traces");

  public TraceFailureHandler(final SlackNotificationService slackNotificationService) {
    this.slackNotificationService = slackNotificationService;
  }

  public void handleCorsetFailure(
      final BlockHeader blockHeader, final CorsetValidator.Result result)
      throws InvalidTraceHandlerException {
    final File traceFile =
        FileUtils.getFile(
            INVALID_TRACE_DIRECTORY,
            "trace_"
                + blockHeader.getNumber()
                + "_"
                + blockHeader.getBlockHash().toHexString()
                + ".lt");
    final File corsetOutputFile =
        FileUtils.getFile(
            INVALID_TRACE_DIRECTORY,
            "corset_output_"
                + blockHeader.getNumber()
                + "_"
                + blockHeader.getBlockHash().toHexString()
                + ".txt");

    try {
      FileUtils.createParentDirectories(traceFile);

      FileUtils.moveFile(FileUtils.getFile(result.traceFile()), traceFile);
      FileUtils.writeStringToFile(corsetOutputFile, result.corsetOutput(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      log.error(
          "Cannot save files for invalid trace to directory {}: {}",
          INVALID_TRACE_DIRECTORY,
          e.getMessage());
      throw new InvalidTraceHandlerException(e);
    }

    try {
      slackNotificationService.sendCorsetFailureNotification(
          blockHeader.getNumber(), blockHeader.getBlockHash().toHexString(), result);
    } catch (IOException e) {
      log.error("Error while sending slack notification: {}", e.getMessage());
      throw new InvalidTraceHandlerException(e);
    }
  }

  public void handleBlockTraceFailure(
      final long blockNumber, final Hash txHash, final Throwable throwable) {
    try {
      slackNotificationService.sendBlockTraceFailureNotification(blockNumber, txHash, throwable);
    } catch (IOException e) {
      log.error("Error while handling block trace failure: {}", e.getMessage());
    }
  }
}
