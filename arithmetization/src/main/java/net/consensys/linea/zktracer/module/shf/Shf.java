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

package net.consensys.linea.zktracer.module.shf;

import java.nio.MappedByteBuffer;
import java.util.List;

import net.consensys.linea.zktracer.ColumnHeader;
import net.consensys.linea.zktracer.container.stacked.set.StackedSet;
import net.consensys.linea.zktracer.module.Module;
import net.consensys.linea.zktracer.opcode.OpCode;
import net.consensys.linea.zktracer.types.UnsignedByte;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;
import org.hyperledger.besu.evm.frame.MessageFrame;

public class Shf implements Module {
  private int stamp = 0;
  private final StackedSet<ShfOperation> operations = new StackedSet<>();

  @Override
  public String jsonKey() {
    return "shf";
  }

  @Override
  public void enterTransaction() {
    this.operations.enter();
  }

  @Override
  public void popTransaction() {
    this.operations.pop();
  }

  @Override
  public void tracePreOpcode(MessageFrame frame) {
    final Bytes32 arg1 = Bytes32.leftPad(frame.getStackItem(0));
    final Bytes32 arg2 = Bytes32.leftPad(frame.getStackItem(1));
    this.operations.add(
        new ShfOperation(OpCode.of(frame.getCurrentOperation().getOpcode()), arg1, arg2));
  }

  private void traceShfOperation(ShfOperation op, Trace trace) {
    this.stamp++;

    for (int i = 0; i < op.maxCt(); i++) {
      final ByteChunks arg2HiByteChunks =
          ByteChunks.fromBytes(UnsignedByte.of(op.arg2Hi().get(i)), op.mshp());
      final ByteChunks arg2LoByteChunks =
          ByteChunks.fromBytes(UnsignedByte.of(op.arg2Lo().get(i)), op.mshp());

      trace
          .acc1(op.arg1Lo().slice(0, 1 + i))
          .acc2(op.arg2Hi().slice(0, 1 + i))
          .acc3(op.arg2Lo().slice(0, 1 + i))
          .acc4(op.res().getResHi().slice(0, 1 + i))
          .acc5(op.res().getResLo().slice(0, 1 + i))
          .arg1Hi(op.arg1Hi())
          .arg1Lo(op.arg1Lo())
          .arg2Hi(op.arg2Hi())
          .arg2Lo(op.arg2Lo());

      if (op.isShiftRight()) {
        trace.bit1(i >= 1).bit2(i >= 2).bit3(i >= 4).bit4(i >= 8);
      } else {
        trace.bit1(i >= (16 - 1)).bit2(i >= (16 - 2)).bit3(i >= (16 - 4)).bit4(i >= (16 - 8));
      }

      trace
          .bitB3(op.isBitB3())
          .bitB4(op.isBitB4())
          .bitB5(op.isBitB5())
          .bitB6(op.isBitB6())
          .bitB7(op.isBitB7())
          .byte1(UnsignedByte.of(op.arg1Lo().get(i)))
          .byte2(UnsignedByte.of(op.arg2Hi().get(i)))
          .byte3(UnsignedByte.of(op.arg2Lo().get(i)))
          .byte4(UnsignedByte.of(op.res().getResHi().get(i)))
          .byte5(UnsignedByte.of(op.res().getResLo().get(i)))
          .bits(op.bits().get(i))
          .counter(Bytes.of(i))
          .inst(Bytes.of(op.opCode().byteValue()))
          .known(op.isKnown())
          .neg(op.isNegative())
          .oneLineInstruction(op.isOneLineInstruction())
          .low3(Bytes.of(op.low3().toInteger()))
          .microShiftParameter(Bytes.ofUnsignedInt(op.mshp().toInteger()))
          .resHi(op.res().getResHi())
          .resLo(op.res().getResLo())
          .leftAlignedSuffixHigh(Bytes.ofUnsignedShort(arg2HiByteChunks.la().toInteger()))
          .rightAlignedPrefixHigh(Bytes.ofUnsignedInt(arg2HiByteChunks.ra().toInteger()))
          .ones(Bytes.ofUnsignedInt(arg2HiByteChunks.ones().toInteger()))
          .leftAlignedSuffixLow(Bytes.ofUnsignedInt(arg2LoByteChunks.la().toInteger()))
          .rightAlignedPrefixLow(Bytes.ofUnsignedInt(arg2LoByteChunks.ra().toInteger()))
          .shb3Hi(Bytes.ofUnsignedInt(op.shb().getShbHi()[0][i].toInteger()))
          .shb3Lo(Bytes.ofUnsignedInt(op.shb().getShbLo()[0][i].toInteger()))
          .shb4Hi(Bytes.ofUnsignedInt(op.shb().getShbHi()[4 - 3][i].toInteger()))
          .shb4Lo(Bytes.ofUnsignedInt(op.shb().getShbLo()[4 - 3][i].toInteger()))
          .shb5Hi(Bytes.ofUnsignedInt(op.shb().getShbHi()[5 - 3][i].toInteger()))
          .shb5Lo(Bytes.ofUnsignedInt(op.shb().getShbLo()[5 - 3][i].toInteger()))
          .shb6Hi(Bytes.ofUnsignedInt(op.shb().getShbHi()[6 - 3][i].toInteger()))
          .shb6Lo(Bytes.ofUnsignedInt(op.shb().getShbLo()[6 - 3][i].toInteger()))
          .shb7Hi(Bytes.ofUnsignedInt(op.shb().getShbHi()[7 - 3][i].toInteger()))
          .shb7Lo(Bytes.ofUnsignedInt(op.shb().getShbLo()[7 - 3][i].toInteger()))
          .shiftDirection(op.isShiftRight())
          .isData(stamp != 0)
          .shiftStamp(Bytes.ofUnsignedInt(stamp))
          .validateRow();
    }
  }

  @Override
  public List<ColumnHeader> columnsHeaders() {
    return Trace.headers(this.lineCount());
  }

  @Override
  public void commit(List<MappedByteBuffer> buffers) {
    final Trace trace = new Trace(buffers);

    for (ShfOperation op : this.operations) {
      this.traceShfOperation(op, trace);
    }
  }

  @Override
  public int lineCount() {
    return this.operations.stream().mapToInt(ShfOperation::maxCt).sum();
  }
}
