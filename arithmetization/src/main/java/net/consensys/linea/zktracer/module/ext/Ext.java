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

package net.consensys.linea.zktracer.module.ext;

import java.nio.MappedByteBuffer;
import java.util.List;

import net.consensys.linea.zktracer.ColumnHeader;
import net.consensys.linea.zktracer.container.stacked.set.StackedSet;
import net.consensys.linea.zktracer.module.Module;
import net.consensys.linea.zktracer.opcode.OpCodeData;
import net.consensys.linea.zktracer.opcode.OpCodes;
import net.consensys.linea.zktracer.types.UnsignedByte;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;
import org.hyperledger.besu.datatypes.Transaction;
import org.hyperledger.besu.evm.frame.MessageFrame;
import org.hyperledger.besu.evm.worldstate.WorldView;

public class Ext implements Module {
  private int stamp = 0;

  /** A set of the operations to trace */
  private final StackedSet<ExtOperation> operations = new StackedSet<>();

  @Override
  public String jsonKey() {
    return "ext";
  }

  @Override
  public void traceStartTx(WorldView worldView, Transaction tx) {
    this.operations.enter();
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
  public void tracePreOpcode(final MessageFrame frame) {
    final OpCodeData opCode = OpCodes.of(frame.getCurrentOperation().getOpcode());
    this.operations.add(
        new ExtOperation(
            opCode.mnemonic(),
            Bytes32.leftPad(frame.getStackItem(0)),
            Bytes32.leftPad(frame.getStackItem(1)),
            Bytes32.leftPad(frame.getStackItem(2))));
  }

  public void traceExtOperation(ExtOperation op, Trace trace) {
    this.stamp++;

    for (int i = 0; i < op.maxCounter(); i++) {
      final int accLength = i + 1;
      trace
          // Byte A and Acc A
          .byteA0(UnsignedByte.of(op.getABytes().get(0).get(i)))
          .byteA1(UnsignedByte.of(op.getABytes().get(1).get(i)))
          .byteA2(UnsignedByte.of(op.getABytes().get(2).get(i)))
          .byteA3(UnsignedByte.of(op.getABytes().get(3).get(i)))
          .accA0(op.getABytes().get(0).slice(0, accLength))
          .accA1(op.getABytes().get(1).slice(0, accLength))
          .accA2(op.getABytes().get(2).slice(0, accLength))
          .accA3(op.getABytes().get(3).slice(0, accLength))
          // Byte B and Acc B
          .byteB0(UnsignedByte.of(op.getBBytes().get(0).get(i)))
          .byteB1(UnsignedByte.of(op.getBBytes().get(1).get(i)))
          .byteB2(UnsignedByte.of(op.getBBytes().get(2).get(i)))
          .byteB3(UnsignedByte.of(op.getBBytes().get(3).get(i)))
          .accB0(op.getBBytes().get(0).slice(0, accLength))
          .accB1(op.getBBytes().get(1).slice(0, accLength))
          .accB2(op.getBBytes().get(2).slice(0, accLength))
          .accB3(op.getBBytes().get(3).slice(0, accLength))
          // Byte C and Acc C
          .byteC0(UnsignedByte.of(op.getCBytes().get(0).get(i)))
          .byteC1(UnsignedByte.of(op.getCBytes().get(1).get(i)))
          .byteC2(UnsignedByte.of(op.getCBytes().get(2).get(i)))
          .byteC3(UnsignedByte.of(op.getCBytes().get(3).get(i)))
          .accC0(op.getCBytes().get(0).slice(0, accLength))
          .accC1(op.getCBytes().get(1).slice(0, accLength))
          .accC2(op.getCBytes().get(2).slice(0, accLength))
          .accC3(op.getCBytes().get(3).slice(0, accLength))
          // Byte Delta and Acc Delta
          .byteDelta0(UnsignedByte.of(op.getDeltaBytes().get(0).get(i)))
          .byteDelta1(UnsignedByte.of(op.getDeltaBytes().get(1).get(i)))
          .byteDelta2(UnsignedByte.of(op.getDeltaBytes().get(2).get(i)))
          .byteDelta3(UnsignedByte.of(op.getDeltaBytes().get(3).get(i)))
          .accDelta0(op.getDeltaBytes().get(0).slice(0, accLength))
          .accDelta1(op.getDeltaBytes().get(1).slice(0, accLength))
          .accDelta2(op.getDeltaBytes().get(2).slice(0, accLength))
          .accDelta3(op.getDeltaBytes().get(3).slice(0, accLength))
          // Byte H and Acc H
          .byteH0(UnsignedByte.of(op.getHBytes().get(0).get(i)))
          .byteH1(UnsignedByte.of(op.getHBytes().get(1).get(i)))
          .byteH2(UnsignedByte.of(op.getHBytes().get(2).get(i)))
          .byteH3(UnsignedByte.of(op.getHBytes().get(3).get(i)))
          .byteH4(UnsignedByte.of(op.getHBytes().get(4).get(i)))
          .byteH5(UnsignedByte.of(op.getHBytes().get(5).get(i)))
          .accH0(op.getHBytes().get(0).slice(0, accLength))
          .accH1(op.getHBytes().get(1).slice(0, accLength))
          .accH2(op.getHBytes().get(2).slice(0, accLength))
          .accH3(op.getHBytes().get(3).slice(0, accLength))
          .accH4(op.getHBytes().get(4).slice(0, accLength))
          .accH5(op.getHBytes().get(5).slice(0, accLength))
          // Byte I and Acc I
          .byteI0(UnsignedByte.of(op.getIBytes().get(0).get(i)))
          .byteI1(UnsignedByte.of(op.getIBytes().get(1).get(i)))
          .byteI2(UnsignedByte.of(op.getIBytes().get(2).get(i)))
          .byteI3(UnsignedByte.of(op.getIBytes().get(3).get(i)))
          .byteI4(UnsignedByte.of(op.getIBytes().get(4).get(i)))
          .byteI5(UnsignedByte.of(op.getIBytes().get(5).get(i)))
          .byteI6(UnsignedByte.of(op.getIBytes().get(6).get(i)))
          .accI0(op.getIBytes().get(0).slice(0, accLength))
          .accI1(op.getIBytes().get(1).slice(0, accLength))
          .accI2(op.getIBytes().get(2).slice(0, accLength))
          .accI3(op.getIBytes().get(3).slice(0, accLength))
          .accI4(op.getIBytes().get(4).slice(0, accLength))
          .accI5(op.getIBytes().get(5).slice(0, accLength))
          .accI6(op.getIBytes().get(6).slice(0, accLength))
          // Byte J and Acc J
          .byteJ0(UnsignedByte.of(op.getJBytes().get(0).get(i)))
          .byteJ1(UnsignedByte.of(op.getJBytes().get(1).get(i)))
          .byteJ2(UnsignedByte.of(op.getJBytes().get(2).get(i)))
          .byteJ3(UnsignedByte.of(op.getJBytes().get(3).get(i)))
          .byteJ4(UnsignedByte.of(op.getJBytes().get(4).get(i)))
          .byteJ5(UnsignedByte.of(op.getJBytes().get(5).get(i)))
          .byteJ6(UnsignedByte.of(op.getJBytes().get(6).get(i)))
          .byteJ7(UnsignedByte.of(op.getJBytes().get(7).get(i)))
          .accJ0(op.getJBytes().get(0).slice(0, accLength))
          .accJ1(op.getJBytes().get(1).slice(0, accLength))
          .accJ2(op.getJBytes().get(2).slice(0, accLength))
          .accJ3(op.getJBytes().get(3).slice(0, accLength))
          .accJ4(op.getJBytes().get(4).slice(0, accLength))
          .accJ5(op.getJBytes().get(5).slice(0, accLength))
          .accJ6(op.getJBytes().get(6).slice(0, accLength))
          .accJ7(op.getJBytes().get(7).slice(0, accLength))
          // Byte Q and Acc Q
          .byteQ0(UnsignedByte.of(op.getQBytes().get(0).get(i)))
          .byteQ1(UnsignedByte.of(op.getQBytes().get(1).get(i)))
          .byteQ2(UnsignedByte.of(op.getQBytes().get(2).get(i)))
          .byteQ3(UnsignedByte.of(op.getQBytes().get(3).get(i)))
          .byteQ4(UnsignedByte.of(op.getQBytes().get(4).get(i)))
          .byteQ5(UnsignedByte.of(op.getQBytes().get(5).get(i)))
          .byteQ6(UnsignedByte.of(op.getQBytes().get(6).get(i)))
          .byteQ7(UnsignedByte.of(op.getQBytes().get(7).get(i)))
          .accQ0(op.getQBytes().get(0).slice(0, accLength))
          .accQ1(op.getQBytes().get(1).slice(0, accLength))
          .accQ2(op.getQBytes().get(2).slice(0, accLength))
          .accQ3(op.getQBytes().get(3).slice(0, accLength))
          .accQ4(op.getQBytes().get(4).slice(0, accLength))
          .accQ5(op.getQBytes().get(5).slice(0, accLength))
          .accQ6(op.getQBytes().get(6).slice(0, accLength))
          .accQ7(op.getQBytes().get(7).slice(0, accLength))
          // Byte R and Acc R
          .byteR0(UnsignedByte.of(op.getRBytes().get(0).get(i)))
          .byteR1(UnsignedByte.of(op.getRBytes().get(1).get(i)))
          .byteR2(UnsignedByte.of(op.getRBytes().get(2).get(i)))
          .byteR3(UnsignedByte.of(op.getRBytes().get(3).get(i)))
          .accR0(op.getRBytes().get(0).slice(0, accLength))
          .accR1(op.getRBytes().get(1).slice(0, accLength))
          .accR2(op.getRBytes().get(2).slice(0, accLength))
          .accR3(op.getRBytes().get(3).slice(0, accLength))
          // other
          .arg1Hi(op.getArg1().getHigh())
          .arg1Lo(op.getArg1().getLow())
          .arg2Hi(op.getArg2().getHigh())
          .arg2Lo(op.getArg2().getLow())
          .arg3Hi(op.getArg3().getHigh())
          .arg3Lo(op.getArg3().getLow())
          .resHi(op.getResult().getHigh())
          .resLo(op.getResult().getLow())
          .cmp(op.getCmp()[i])
          .ofH(op.getOverflowH()[i])
          .ofJ(op.getOverflowJ()[i])
          .ofI(op.getOverflowI()[i])
          .ofRes(op.getOverflowRes()[i])
          .ct(Bytes.of(i))
          .inst(Bytes.of(op.getOpCode().byteValue()))
          .oli(op.isOli())
          .bit1(op.getBit1())
          .bit2(op.getBit2())
          .bit3(op.getBit3())
          .stamp(Bytes.ofUnsignedLong(stamp))
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
    for (ExtOperation operation : this.operations) {
      operation.setup();
      this.traceExtOperation(operation, trace);
    }
  }

  @Override
  public int lineCount() {
    return this.operations.stream().mapToInt(ExtOperation::maxCounter).sum();
  }
}
