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

package net.consensys.linea.zktracer.module.preclimits;

import static net.consensys.linea.zktracer.module.Util.slice;

import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.util.List;
import java.util.Stack;

import lombok.RequiredArgsConstructor;
import net.consensys.linea.zktracer.ColumnHeader;
import net.consensys.linea.zktracer.module.Module;
import net.consensys.linea.zktracer.module.hub.Hub;
import net.consensys.linea.zktracer.opcode.OpCode;
import org.apache.tuweni.bytes.Bytes;
import org.hyperledger.besu.datatypes.Address;
import org.hyperledger.besu.evm.frame.MessageFrame;
import org.hyperledger.besu.evm.internal.Words;

@RequiredArgsConstructor
public final class EcRec implements Module {
  private final Hub hub;
  private final Stack<Integer> counts = new Stack<Integer>();

  @Override
  public String jsonKey() {
    return "ecrecover";
  }

  private final int ecrecGasFee = 3000;
  private final int ewordSize = 32;
  private final BigInteger secp256k1n =
      new BigInteger(
          "115792089237316195423570985008687907852837564279074904382605163141518161494337");

  @Override
  public void enterTransaction() {
    counts.push(0);
  }

  @Override
  public void popTransaction() {
    counts.pop();
  }

  @Override
  public void tracePreOpcode(MessageFrame frame) {
    final OpCode opCode = hub.opCode();

    switch (opCode) {
      case CALL, STATICCALL, DELEGATECALL, CALLCODE -> {
        final Address target = Words.toAddress(frame.getStackItem(1));
        if (target == Address.ECREC) {
          long length = 0;
          long offset = 0;
          switch (opCode) {
            case CALL, CALLCODE -> {
              length = Words.clampedToLong(frame.getStackItem(4));
              offset = Words.clampedToLong(frame.getStackItem(3));
            }
            case DELEGATECALL, STATICCALL -> {
              length = Words.clampedToLong(frame.getStackItem(3));
              offset = Words.clampedToLong(frame.getStackItem(2));
            }
          }
          final Bytes inputData = frame.shadowReadMemory(offset, length);
          final BigInteger h = slice(inputData, 0, ewordSize).toUnsignedBigInteger();
          final BigInteger v = slice(inputData, ewordSize, ewordSize).toUnsignedBigInteger();
          final BigInteger r = slice(inputData, ewordSize * 2, ewordSize).toUnsignedBigInteger();
          final BigInteger s = slice(inputData, ewordSize * 3, ewordSize).toUnsignedBigInteger();
          final long gasPaid = Words.clampedToLong(frame.getStackItem(0));
          // TODO: exclude case without valid signature
          if (gasPaid >= ecrecGasFee
              && (v.equals(BigInteger.valueOf(27)) || v.equals(BigInteger.valueOf(28)))
              && !r.equals(BigInteger.ZERO)
              && r.compareTo(secp256k1n) < 0
              && !s.equals(BigInteger.ZERO)
              && s.compareTo(secp256k1n) < 0) {
            this.counts.push(this.counts.pop() + 1);
          }
        }
      }
      default -> {}
    }
  }

  @Override
  public int lineCount() {
    return this.counts.stream().mapToInt(x -> x).sum();
  }

  @Override
  public List<ColumnHeader> columnsHeaders() {
    throw new IllegalStateException("should never be called");
  }

  @Override
  public void commit(List<MappedByteBuffer> buffers) {
    throw new IllegalStateException("should never be called");
  }
}
