/*
 * Copyright ConsenSys Inc.
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

package net.consensys.linea.zktracer.module.wcp;

import java.nio.MappedByteBuffer;
import java.util.BitSet;
import java.util.List;

import net.consensys.linea.zktracer.ColumnHeader;
import net.consensys.linea.zktracer.types.UnsignedByte;
import org.apache.tuweni.bytes.Bytes;

/**
 * WARNING: This code is generated automatically.
 *
 * <p>Any modifications to this code may be overwritten and could lead to unexpected behavior.
 * Please DO NOT ATTEMPT TO MODIFY this code directly.
 */
public class Trace {

  private final BitSet filled = new BitSet();
  private int currentLine = 0;

  private final MappedByteBuffer acc1;
  private final MappedByteBuffer acc2;
  private final MappedByteBuffer acc3;
  private final MappedByteBuffer acc4;
  private final MappedByteBuffer acc5;
  private final MappedByteBuffer acc6;
  private final MappedByteBuffer argument1Hi;
  private final MappedByteBuffer argument1Lo;
  private final MappedByteBuffer argument2Hi;
  private final MappedByteBuffer argument2Lo;
  private final MappedByteBuffer bit1;
  private final MappedByteBuffer bit2;
  private final MappedByteBuffer bit3;
  private final MappedByteBuffer bit4;
  private final MappedByteBuffer bits;
  private final MappedByteBuffer byte1;
  private final MappedByteBuffer byte2;
  private final MappedByteBuffer byte3;
  private final MappedByteBuffer byte4;
  private final MappedByteBuffer byte5;
  private final MappedByteBuffer byte6;
  private final MappedByteBuffer counter;
  private final MappedByteBuffer inst;
  private final MappedByteBuffer neg1;
  private final MappedByteBuffer neg2;
  private final MappedByteBuffer oneLineInstruction;
  private final MappedByteBuffer resultHi;
  private final MappedByteBuffer resultLo;
  private final MappedByteBuffer wordComparisonStamp;

  static List<ColumnHeader> headers(int length) {
    return List.of(
        new ColumnHeader("wcp.ACC_1", 32, length),
        new ColumnHeader("wcp.ACC_2", 32, length),
        new ColumnHeader("wcp.ACC_3", 32, length),
        new ColumnHeader("wcp.ACC_4", 32, length),
        new ColumnHeader("wcp.ACC_5", 32, length),
        new ColumnHeader("wcp.ACC_6", 32, length),
        new ColumnHeader("wcp.ARGUMENT_1_HI", 32, length),
        new ColumnHeader("wcp.ARGUMENT_1_LO", 32, length),
        new ColumnHeader("wcp.ARGUMENT_2_HI", 32, length),
        new ColumnHeader("wcp.ARGUMENT_2_LO", 32, length),
        new ColumnHeader("wcp.BIT_1", 1, length),
        new ColumnHeader("wcp.BIT_2", 1, length),
        new ColumnHeader("wcp.BIT_3", 1, length),
        new ColumnHeader("wcp.BIT_4", 1, length),
        new ColumnHeader("wcp.BITS", 1, length),
        new ColumnHeader("wcp.BYTE_1", 1, length),
        new ColumnHeader("wcp.BYTE_2", 1, length),
        new ColumnHeader("wcp.BYTE_3", 1, length),
        new ColumnHeader("wcp.BYTE_4", 1, length),
        new ColumnHeader("wcp.BYTE_5", 1, length),
        new ColumnHeader("wcp.BYTE_6", 1, length),
        new ColumnHeader("wcp.COUNTER", 32, length),
        new ColumnHeader("wcp.INST", 32, length),
        new ColumnHeader("wcp.NEG_1", 1, length),
        new ColumnHeader("wcp.NEG_2", 1, length),
        new ColumnHeader("wcp.ONE_LINE_INSTRUCTION", 1, length),
        new ColumnHeader("wcp.RESULT_HI", 32, length),
        new ColumnHeader("wcp.RESULT_LO", 32, length),
        new ColumnHeader("wcp.WORD_COMPARISON_STAMP", 32, length));
  }

  public Trace(List<MappedByteBuffer> buffers) {
    this.acc1 = buffers.get(0);
    this.acc2 = buffers.get(1);
    this.acc3 = buffers.get(2);
    this.acc4 = buffers.get(3);
    this.acc5 = buffers.get(4);
    this.acc6 = buffers.get(5);
    this.argument1Hi = buffers.get(6);
    this.argument1Lo = buffers.get(7);
    this.argument2Hi = buffers.get(8);
    this.argument2Lo = buffers.get(9);
    this.bit1 = buffers.get(10);
    this.bit2 = buffers.get(11);
    this.bit3 = buffers.get(12);
    this.bit4 = buffers.get(13);
    this.bits = buffers.get(14);
    this.byte1 = buffers.get(15);
    this.byte2 = buffers.get(16);
    this.byte3 = buffers.get(17);
    this.byte4 = buffers.get(18);
    this.byte5 = buffers.get(19);
    this.byte6 = buffers.get(20);
    this.counter = buffers.get(21);
    this.inst = buffers.get(22);
    this.neg1 = buffers.get(23);
    this.neg2 = buffers.get(24);
    this.oneLineInstruction = buffers.get(25);
    this.resultHi = buffers.get(26);
    this.resultLo = buffers.get(27);
    this.wordComparisonStamp = buffers.get(28);
  }

  public int size() {
    if (!filled.isEmpty()) {
      throw new RuntimeException("Cannot measure a trace with a non-validated row.");
    }

    return this.currentLine;
  }

  public Trace acc1(final Bytes b) {
    if (filled.get(0)) {
      throw new IllegalStateException("wcp.ACC_1 already set");
    } else {
      filled.set(0);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      acc1.put((byte) 0);
    }
    acc1.put(b.toArrayUnsafe());

    return this;
  }

  public Trace acc2(final Bytes b) {
    if (filled.get(1)) {
      throw new IllegalStateException("wcp.ACC_2 already set");
    } else {
      filled.set(1);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      acc2.put((byte) 0);
    }
    acc2.put(b.toArrayUnsafe());

    return this;
  }

  public Trace acc3(final Bytes b) {
    if (filled.get(2)) {
      throw new IllegalStateException("wcp.ACC_3 already set");
    } else {
      filled.set(2);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      acc3.put((byte) 0);
    }
    acc3.put(b.toArrayUnsafe());

    return this;
  }

  public Trace acc4(final Bytes b) {
    if (filled.get(3)) {
      throw new IllegalStateException("wcp.ACC_4 already set");
    } else {
      filled.set(3);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      acc4.put((byte) 0);
    }
    acc4.put(b.toArrayUnsafe());

    return this;
  }

  public Trace acc5(final Bytes b) {
    if (filled.get(4)) {
      throw new IllegalStateException("wcp.ACC_5 already set");
    } else {
      filled.set(4);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      acc5.put((byte) 0);
    }
    acc5.put(b.toArrayUnsafe());

    return this;
  }

  public Trace acc6(final Bytes b) {
    if (filled.get(5)) {
      throw new IllegalStateException("wcp.ACC_6 already set");
    } else {
      filled.set(5);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      acc6.put((byte) 0);
    }
    acc6.put(b.toArrayUnsafe());

    return this;
  }

  public Trace argument1Hi(final Bytes b) {
    if (filled.get(6)) {
      throw new IllegalStateException("wcp.ARGUMENT_1_HI already set");
    } else {
      filled.set(6);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      argument1Hi.put((byte) 0);
    }
    argument1Hi.put(b.toArrayUnsafe());

    return this;
  }

  public Trace argument1Lo(final Bytes b) {
    if (filled.get(7)) {
      throw new IllegalStateException("wcp.ARGUMENT_1_LO already set");
    } else {
      filled.set(7);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      argument1Lo.put((byte) 0);
    }
    argument1Lo.put(b.toArrayUnsafe());

    return this;
  }

  public Trace argument2Hi(final Bytes b) {
    if (filled.get(8)) {
      throw new IllegalStateException("wcp.ARGUMENT_2_HI already set");
    } else {
      filled.set(8);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      argument2Hi.put((byte) 0);
    }
    argument2Hi.put(b.toArrayUnsafe());

    return this;
  }

  public Trace argument2Lo(final Bytes b) {
    if (filled.get(9)) {
      throw new IllegalStateException("wcp.ARGUMENT_2_LO already set");
    } else {
      filled.set(9);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      argument2Lo.put((byte) 0);
    }
    argument2Lo.put(b.toArrayUnsafe());

    return this;
  }

  public Trace bit1(final Boolean b) {
    if (filled.get(11)) {
      throw new IllegalStateException("wcp.BIT_1 already set");
    } else {
      filled.set(11);
    }

    bit1.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace bit2(final Boolean b) {
    if (filled.get(12)) {
      throw new IllegalStateException("wcp.BIT_2 already set");
    } else {
      filled.set(12);
    }

    bit2.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace bit3(final Boolean b) {
    if (filled.get(13)) {
      throw new IllegalStateException("wcp.BIT_3 already set");
    } else {
      filled.set(13);
    }

    bit3.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace bit4(final Boolean b) {
    if (filled.get(14)) {
      throw new IllegalStateException("wcp.BIT_4 already set");
    } else {
      filled.set(14);
    }

    bit4.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace bits(final Boolean b) {
    if (filled.get(10)) {
      throw new IllegalStateException("wcp.BITS already set");
    } else {
      filled.set(10);
    }

    bits.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace byte1(final UnsignedByte b) {
    if (filled.get(15)) {
      throw new IllegalStateException("wcp.BYTE_1 already set");
    } else {
      filled.set(15);
    }

    byte1.put(b.toByte());

    return this;
  }

  public Trace byte2(final UnsignedByte b) {
    if (filled.get(16)) {
      throw new IllegalStateException("wcp.BYTE_2 already set");
    } else {
      filled.set(16);
    }

    byte2.put(b.toByte());

    return this;
  }

  public Trace byte3(final UnsignedByte b) {
    if (filled.get(17)) {
      throw new IllegalStateException("wcp.BYTE_3 already set");
    } else {
      filled.set(17);
    }

    byte3.put(b.toByte());

    return this;
  }

  public Trace byte4(final UnsignedByte b) {
    if (filled.get(18)) {
      throw new IllegalStateException("wcp.BYTE_4 already set");
    } else {
      filled.set(18);
    }

    byte4.put(b.toByte());

    return this;
  }

  public Trace byte5(final UnsignedByte b) {
    if (filled.get(19)) {
      throw new IllegalStateException("wcp.BYTE_5 already set");
    } else {
      filled.set(19);
    }

    byte5.put(b.toByte());

    return this;
  }

  public Trace byte6(final UnsignedByte b) {
    if (filled.get(20)) {
      throw new IllegalStateException("wcp.BYTE_6 already set");
    } else {
      filled.set(20);
    }

    byte6.put(b.toByte());

    return this;
  }

  public Trace counter(final Bytes b) {
    if (filled.get(21)) {
      throw new IllegalStateException("wcp.COUNTER already set");
    } else {
      filled.set(21);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      counter.put((byte) 0);
    }
    counter.put(b.toArrayUnsafe());

    return this;
  }

  public Trace inst(final Bytes b) {
    if (filled.get(22)) {
      throw new IllegalStateException("wcp.INST already set");
    } else {
      filled.set(22);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      inst.put((byte) 0);
    }
    inst.put(b.toArrayUnsafe());

    return this;
  }

  public Trace neg1(final Boolean b) {
    if (filled.get(23)) {
      throw new IllegalStateException("wcp.NEG_1 already set");
    } else {
      filled.set(23);
    }

    neg1.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace neg2(final Boolean b) {
    if (filled.get(24)) {
      throw new IllegalStateException("wcp.NEG_2 already set");
    } else {
      filled.set(24);
    }

    neg2.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace oneLineInstruction(final Boolean b) {
    if (filled.get(25)) {
      throw new IllegalStateException("wcp.ONE_LINE_INSTRUCTION already set");
    } else {
      filled.set(25);
    }

    oneLineInstruction.put((byte) (b ? 1 : 0));

    return this;
  }

  public Trace resultHi(final Bytes b) {
    if (filled.get(26)) {
      throw new IllegalStateException("wcp.RESULT_HI already set");
    } else {
      filled.set(26);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      resultHi.put((byte) 0);
    }
    resultHi.put(b.toArrayUnsafe());

    return this;
  }

  public Trace resultLo(final Bytes b) {
    if (filled.get(27)) {
      throw new IllegalStateException("wcp.RESULT_LO already set");
    } else {
      filled.set(27);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      resultLo.put((byte) 0);
    }
    resultLo.put(b.toArrayUnsafe());

    return this;
  }

  public Trace wordComparisonStamp(final Bytes b) {
    if (filled.get(28)) {
      throw new IllegalStateException("wcp.WORD_COMPARISON_STAMP already set");
    } else {
      filled.set(28);
    }

    final byte[] bs = b.toArrayUnsafe();
    for (int i = bs.length; i < 32; i++) {
      wordComparisonStamp.put((byte) 0);
    }
    wordComparisonStamp.put(b.toArrayUnsafe());

    return this;
  }

  public Trace validateRow() {
    if (!filled.get(0)) {
      throw new IllegalStateException("wcp.ACC_1 has not been filled");
    }

    if (!filled.get(1)) {
      throw new IllegalStateException("wcp.ACC_2 has not been filled");
    }

    if (!filled.get(2)) {
      throw new IllegalStateException("wcp.ACC_3 has not been filled");
    }

    if (!filled.get(3)) {
      throw new IllegalStateException("wcp.ACC_4 has not been filled");
    }

    if (!filled.get(4)) {
      throw new IllegalStateException("wcp.ACC_5 has not been filled");
    }

    if (!filled.get(5)) {
      throw new IllegalStateException("wcp.ACC_6 has not been filled");
    }

    if (!filled.get(6)) {
      throw new IllegalStateException("wcp.ARGUMENT_1_HI has not been filled");
    }

    if (!filled.get(7)) {
      throw new IllegalStateException("wcp.ARGUMENT_1_LO has not been filled");
    }

    if (!filled.get(8)) {
      throw new IllegalStateException("wcp.ARGUMENT_2_HI has not been filled");
    }

    if (!filled.get(9)) {
      throw new IllegalStateException("wcp.ARGUMENT_2_LO has not been filled");
    }

    if (!filled.get(11)) {
      throw new IllegalStateException("wcp.BIT_1 has not been filled");
    }

    if (!filled.get(12)) {
      throw new IllegalStateException("wcp.BIT_2 has not been filled");
    }

    if (!filled.get(13)) {
      throw new IllegalStateException("wcp.BIT_3 has not been filled");
    }

    if (!filled.get(14)) {
      throw new IllegalStateException("wcp.BIT_4 has not been filled");
    }

    if (!filled.get(10)) {
      throw new IllegalStateException("wcp.BITS has not been filled");
    }

    if (!filled.get(15)) {
      throw new IllegalStateException("wcp.BYTE_1 has not been filled");
    }

    if (!filled.get(16)) {
      throw new IllegalStateException("wcp.BYTE_2 has not been filled");
    }

    if (!filled.get(17)) {
      throw new IllegalStateException("wcp.BYTE_3 has not been filled");
    }

    if (!filled.get(18)) {
      throw new IllegalStateException("wcp.BYTE_4 has not been filled");
    }

    if (!filled.get(19)) {
      throw new IllegalStateException("wcp.BYTE_5 has not been filled");
    }

    if (!filled.get(20)) {
      throw new IllegalStateException("wcp.BYTE_6 has not been filled");
    }

    if (!filled.get(21)) {
      throw new IllegalStateException("wcp.COUNTER has not been filled");
    }

    if (!filled.get(22)) {
      throw new IllegalStateException("wcp.INST has not been filled");
    }

    if (!filled.get(23)) {
      throw new IllegalStateException("wcp.NEG_1 has not been filled");
    }

    if (!filled.get(24)) {
      throw new IllegalStateException("wcp.NEG_2 has not been filled");
    }

    if (!filled.get(25)) {
      throw new IllegalStateException("wcp.ONE_LINE_INSTRUCTION has not been filled");
    }

    if (!filled.get(26)) {
      throw new IllegalStateException("wcp.RESULT_HI has not been filled");
    }

    if (!filled.get(27)) {
      throw new IllegalStateException("wcp.RESULT_LO has not been filled");
    }

    if (!filled.get(28)) {
      throw new IllegalStateException("wcp.WORD_COMPARISON_STAMP has not been filled");
    }

    filled.clear();
    this.currentLine++;

    return this;
  }

  public Trace fillAndValidateRow() {
    if (!filled.get(0)) {
      acc1.position(acc1.position() + 32);
    }

    if (!filled.get(1)) {
      acc2.position(acc2.position() + 32);
    }

    if (!filled.get(2)) {
      acc3.position(acc3.position() + 32);
    }

    if (!filled.get(3)) {
      acc4.position(acc4.position() + 32);
    }

    if (!filled.get(4)) {
      acc5.position(acc5.position() + 32);
    }

    if (!filled.get(5)) {
      acc6.position(acc6.position() + 32);
    }

    if (!filled.get(6)) {
      argument1Hi.position(argument1Hi.position() + 32);
    }

    if (!filled.get(7)) {
      argument1Lo.position(argument1Lo.position() + 32);
    }

    if (!filled.get(8)) {
      argument2Hi.position(argument2Hi.position() + 32);
    }

    if (!filled.get(9)) {
      argument2Lo.position(argument2Lo.position() + 32);
    }

    if (!filled.get(11)) {
      bit1.position(bit1.position() + 1);
    }

    if (!filled.get(12)) {
      bit2.position(bit2.position() + 1);
    }

    if (!filled.get(13)) {
      bit3.position(bit3.position() + 1);
    }

    if (!filled.get(14)) {
      bit4.position(bit4.position() + 1);
    }

    if (!filled.get(10)) {
      bits.position(bits.position() + 1);
    }

    if (!filled.get(15)) {
      byte1.position(byte1.position() + 1);
    }

    if (!filled.get(16)) {
      byte2.position(byte2.position() + 1);
    }

    if (!filled.get(17)) {
      byte3.position(byte3.position() + 1);
    }

    if (!filled.get(18)) {
      byte4.position(byte4.position() + 1);
    }

    if (!filled.get(19)) {
      byte5.position(byte5.position() + 1);
    }

    if (!filled.get(20)) {
      byte6.position(byte6.position() + 1);
    }

    if (!filled.get(21)) {
      counter.position(counter.position() + 32);
    }

    if (!filled.get(22)) {
      inst.position(inst.position() + 32);
    }

    if (!filled.get(23)) {
      neg1.position(neg1.position() + 1);
    }

    if (!filled.get(24)) {
      neg2.position(neg2.position() + 1);
    }

    if (!filled.get(25)) {
      oneLineInstruction.position(oneLineInstruction.position() + 1);
    }

    if (!filled.get(26)) {
      resultHi.position(resultHi.position() + 32);
    }

    if (!filled.get(27)) {
      resultLo.position(resultLo.position() + 32);
    }

    if (!filled.get(28)) {
      wordComparisonStamp.position(wordComparisonStamp.position() + 32);
    }

    filled.clear();
    this.currentLine++;

    return this;
  }

  public void build() {
    if (!filled.isEmpty()) {
      throw new IllegalStateException("Cannot build trace with a non-validated row.");
    }
  }
}
