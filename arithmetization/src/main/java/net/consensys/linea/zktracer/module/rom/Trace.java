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

package net.consensys.linea.zktracer.module.rom;

import java.math.BigInteger;
import java.util.BitSet;

import net.consensys.linea.zktracer.types.UnsignedByte;

/**
 * WARNING: This code is generated automatically. Any modifications to this code may be overwritten
 * and could lead to unexpected behavior. Please DO NOT ATTEMPT TO MODIFY this code directly.
 */
public class Trace {

  private int lineCounter;
  private BigInteger acc;
  private BigInteger codeFragmentIndex;
  private BigInteger codeFragmentIndexInfty;
  private BigInteger codeSize;
  private Boolean codesizeReached;
  private BigInteger counter;
  private BigInteger counterMax;
  private BigInteger counterPush;
  private BigInteger index;
  private Boolean isPush;
  private Boolean isPushData;
  private  BigInteger limb;
  private BigInteger nBytes;
  private BigInteger nBytesAcc;
  private UnsignedByte opcode;
  private UnsignedByte paddedBytecodeByte;
  private BigInteger programmeCounter;
  private Boolean pushFunnelBit;
  private BigInteger pushParameter;
  private BigInteger pushValueAcc;
  private BigInteger pushValueHigh;
  private BigInteger pushValueLow;
  private Boolean validJumpDestination;

  public Trace(int lineCounter, BigInteger acc, BigInteger codeFragmentIndex, BigInteger codeFragmentIndexInfty, BigInteger codeSize, Boolean codesizeReached, BigInteger counter, BigInteger counterMax, BigInteger counterPush, BigInteger index, Boolean isPush, Boolean isPushData, BigInteger limb, BigInteger nBytes, BigInteger nBytesAcc, UnsignedByte opcode, UnsignedByte paddedBytecodeByte, BigInteger programmeCounter, Boolean pushFunnelBit, BigInteger pushParameter, BigInteger pushValueAcc, BigInteger pushValueHigh, BigInteger pushValueLow, Boolean validJumpDestination) {
    this.lineCounter = lineCounter;
    this.acc = acc;
    this.codeFragmentIndex = codeFragmentIndex;
    this.codeFragmentIndexInfty = codeFragmentIndexInfty;
    this.codeSize = codeSize;
    this.codesizeReached = codesizeReached;
    this.counter = counter;
    this.counterMax = counterMax;
    this.counterPush = counterPush;
    this.index = index;
    this.isPush = isPush;
    this.isPushData = isPushData;
    this.limb = limb;
    this.nBytes = nBytes;
    this.nBytesAcc = nBytesAcc;
    this.opcode = opcode;
    this.paddedBytecodeByte = paddedBytecodeByte;
    this.programmeCounter = programmeCounter;
    this.pushFunnelBit = pushFunnelBit;
    this.pushParameter = pushParameter;
    this.pushValueAcc = pushValueAcc;
    this.pushValueHigh = pushValueHigh;
    this.pushValueLow = pushValueLow;
    this.validJumpDestination = validJumpDestination;
  }

  public BigInteger getAcc() {
    return acc;
  }

  public BigInteger getCodeFragmentIndex() {
    return codeFragmentIndex;
  }

  public BigInteger getCodeFragmentIndexInfty() {
    return codeFragmentIndexInfty;
  }

  public BigInteger getCodeSize() {
    return codeSize;
  }

  public Boolean getCodesizeReached() {
    return codesizeReached;
  }

  public BigInteger getCounter() {
    return counter;
  }

  public BigInteger getCounterMax() {
    return counterMax;
  }

  public BigInteger getCounterPush() {
    return counterPush;
  }

  public BigInteger getIndex() {
    return index;
  }

  public Boolean getPush() {
    return isPush;
  }

  public Boolean getPushData() {
    return isPushData;
  }

  public BigInteger getLimb() {
    return limb;
  }

  public BigInteger getnBytes() {
    return nBytes;
  }

  public BigInteger getnBytesAcc() {
    return nBytesAcc;
  }

  public UnsignedByte getOpcode() {
    return opcode;
  }

  public UnsignedByte getPaddedBytecodeByte() {
    return paddedBytecodeByte;
  }

  public BigInteger getProgrammeCounter() {
    return programmeCounter;
  }

  public Boolean getPushFunnelBit() {
    return pushFunnelBit;
  }

  public BigInteger getPushParameter() {
    return pushParameter;
  }

  public BigInteger getPushValueAcc() {
    return pushValueAcc;
  }

  public BigInteger getPushValueHigh() {
    return pushValueHigh;
  }

  public BigInteger getPushValueLow() {
    return pushValueLow;
  }

  public Boolean getValidJumpDestination() {
    return validJumpDestination;
  }

  static TraceBuilder builder(String formattedDate) {
    return new TraceBuilder("rom",formattedDate);
  }

  public int size() {
    return lineCounter;
  }

  static class TraceBuilder {
    private final BitSet filled = new BitSet();
    private final String moduleName;
    private int lineCounter = 0;
    private String formattedDate;

    public TraceBuilder(final String moduleName, final String formattedDate) {
      this.moduleName = moduleName;
      this.formattedDate = formattedDate;
    }

    private BigInteger acc;

    private BigInteger codeFragmentIndex;

    private BigInteger codeFragmentIndexInfty;

    private BigInteger codeSize;

    private Boolean codesizeReached;

    private BigInteger counter;
    
    private BigInteger counterMax;

    private BigInteger counterPush;

    private BigInteger index;

    private Boolean isPush;

    private Boolean isPushData;

    private BigInteger limb;

    private BigInteger nBytes;

    private BigInteger nBytesAcc;

    private UnsignedByte opcode;

    private UnsignedByte paddedBytecodeByte;

    private BigInteger programmeCounter;

    private Boolean pushFunnelBit;

    private BigInteger pushParameter;

    private BigInteger pushValueAcc;

    private BigInteger pushValueHigh;

    private BigInteger pushValueLow;

    private Boolean validJumpDestination;

    public int size() {
      if (!filled.isEmpty()) {
        throw new RuntimeException("Cannot measure a trace with a non-validated row.");
      }

      return lineCounter;
    }

    public TraceBuilder acc(final BigInteger b) {
      if (filled.get(0)) {
        throw new IllegalStateException("ACC already set");
      } else {
        filled.set(0);
      }

      acc = b;
      lineCounter++;
      return this;
    }

    public TraceBuilder codeFragmentIndex(final BigInteger b) {
      if (filled.get(2)) {
        throw new IllegalStateException("CODE_FRAGMENT_INDEX already set");
      } else {
        filled.set(2);
      }

      codeFragmentIndex=b;

      return this;
    }

    public TraceBuilder codeFragmentIndexInfty(final BigInteger b) {
      if (filled.get(3)) {
        throw new IllegalStateException("CODE_FRAGMENT_INDEX_INFTY already set");
      } else {
        filled.set(3);
      }

      codeFragmentIndexInfty=b;

      return this;
    }

    public TraceBuilder codeSize(final BigInteger b) {
      if (filled.get(4)) {
        throw new IllegalStateException("CODE_SIZE already set");
      } else {
        filled.set(4);
      }

      codeSize=b;

      return this;
    }

    public TraceBuilder codesizeReached(final Boolean b) {
      if (filled.get(1)) {
        throw new IllegalStateException("CODESIZE_REACHED already set");
      } else {
        filled.set(1);
      }

      codesizeReached=b;

      return this;
    }

    public TraceBuilder counter(final BigInteger b) {
      if (filled.get(5)) {
        throw new IllegalStateException("COUNTER already set");
      } else {
        filled.set(5);
      }

      counter=b;

      return this;
    }

    public TraceBuilder counterMax(final BigInteger b) {
      if (filled.get(6)) {
        throw new IllegalStateException("COUNTER_MAX already set");
      } else {
        filled.set(6);
      }

      counterMax=b;

      return this;
    }

    public TraceBuilder counterPush(final BigInteger b) {
      if (filled.get(7)) {
        throw new IllegalStateException("COUNTER_PUSH already set");
      } else {
        filled.set(7);
      }

      counterPush=b;

      return this;
    }

    public TraceBuilder index(final BigInteger b) {
      if (filled.get(8)) {
        throw new IllegalStateException("INDEX already set");
      } else {
        filled.set(8);
      }

      index=b;

      return this;
    }

    public TraceBuilder isPush(final Boolean b) {
      if (filled.get(9)) {
        throw new IllegalStateException("IS_PUSH already set");
      } else {
        filled.set(9);
      }

      isPush=b;

      return this;
    }

    public TraceBuilder isPushData(final Boolean b) {
      if (filled.get(10)) {
        throw new IllegalStateException("IS_PUSH_DATA already set");
      } else {
        filled.set(10);
      }

      isPushData=b;

      return this;
    }

    public TraceBuilder limb(final BigInteger b) {
      if (filled.get(11)) {
        throw new IllegalStateException("LIMB already set");
      } else {
        filled.set(11);
      }

      limb=b;

      return this;
    }

    public TraceBuilder nBytes(final BigInteger b) {
      if (filled.get(21)) {
        throw new IllegalStateException("nBYTES already set");
      } else {
        filled.set(21);
      }

      nBytes=b;

      return this;
    }

    public TraceBuilder nBytesAcc(final BigInteger b) {
      if (filled.get(22)) {
        throw new IllegalStateException("nBYTES_ACC already set");
      } else {
        filled.set(22);
      }

      nBytesAcc=b;

      return this;
    }

    public TraceBuilder opcode(final UnsignedByte b) {
      if (filled.get(12)) {
        throw new IllegalStateException("OPCODE already set");
      } else {
        filled.set(12);
      }

      opcode=b;

      return this;
    }

    public TraceBuilder paddedBytecodeByte(final UnsignedByte b) {
      if (filled.get(13)) {
        throw new IllegalStateException("PADDED_BYTECODE_BYTE already set");
      } else {
        filled.set(13);
      }

      paddedBytecodeByte=b;

      return this;
    }

    public TraceBuilder programmeCounter(final BigInteger b) {
      if (filled.get(14)) {
        throw new IllegalStateException("PROGRAMME_COUNTER already set");
      } else {
        filled.set(14);
      }

      programmeCounter=b;

      return this;
    }

    public TraceBuilder pushFunnelBit(final Boolean b) {
      if (filled.get(15)) {
        throw new IllegalStateException("PUSH_FUNNEL_BIT already set");
      } else {
        filled.set(15);
      }

      pushFunnelBit=b;

      return this;
    }

    public TraceBuilder pushParameter(final BigInteger b) {
      if (filled.get(16)) {
        throw new IllegalStateException("PUSH_PARAMETER already set");
      } else {
        filled.set(16);
      }

      pushParameter=b;

      return this;
    }

    public TraceBuilder pushValueAcc(final BigInteger b) {
      if (filled.get(17)) {
        throw new IllegalStateException("PUSH_VALUE_ACC already set");
      } else {
        filled.set(17);
      }

      pushValueAcc=b;

      return this;
    }

    public TraceBuilder pushValueHigh(final BigInteger b) {
      if (filled.get(18)) {
        throw new IllegalStateException("PUSH_VALUE_HIGH already set");
      } else {
        filled.set(18);
      }

      pushValueHigh=b;

      return this;
    }

    public TraceBuilder pushValueLow(final BigInteger b) {
      if (filled.get(19)) {
        throw new IllegalStateException("PUSH_VALUE_LOW already set");
      } else {
        filled.set(19);
      }

      pushValueLow=b;

      return this;
    }

    public TraceBuilder validJumpDestination(final Boolean b) {
      if (filled.get(20)) {
        throw new IllegalStateException("VALID_JUMP_DESTINATION already set");
      } else {
        filled.set(20);
      }

      validJumpDestination=b;

      return this;
    }

    public TraceBuilder validateRow() {
      if (!filled.get(0)) {
        throw new IllegalStateException("ACC has not been filled");
      }

      if (!filled.get(2)) {
        throw new IllegalStateException("CODE_FRAGMENT_INDEX has not been filled");
      }

      if (!filled.get(3)) {
        throw new IllegalStateException("CODE_FRAGMENT_INDEX_INFTY has not been filled");
      }

      if (!filled.get(4)) {
        throw new IllegalStateException("CODE_SIZE has not been filled");
      }

      if (!filled.get(1)) {
        throw new IllegalStateException("CODESIZE_REACHED has not been filled");
      }

      if (!filled.get(5)) {
        throw new IllegalStateException("COUNTER has not been filled");
      }

      if (!filled.get(6)) {
        throw new IllegalStateException("COUNTER_MAX has not been filled");
      }

      if (!filled.get(7)) {
        throw new IllegalStateException("COUNTER_PUSH has not been filled");
      }

      if (!filled.get(8)) {
        throw new IllegalStateException("INDEX has not been filled");
      }

      if (!filled.get(9)) {
        throw new IllegalStateException("IS_PUSH has not been filled");
      }

      if (!filled.get(10)) {
        throw new IllegalStateException("IS_PUSH_DATA has not been filled");
      }

      if (!filled.get(11)) {
        throw new IllegalStateException("LIMB has not been filled");
      }

      if (!filled.get(21)) {
        throw new IllegalStateException("nBYTES has not been filled");
      }

      if (!filled.get(22)) {
        throw new IllegalStateException("nBYTES_ACC has not been filled");
      }

      if (!filled.get(12)) {
        throw new IllegalStateException("OPCODE has not been filled");
      }

      if (!filled.get(13)) {
        throw new IllegalStateException("PADDED_BYTECODE_BYTE has not been filled");
      }

      if (!filled.get(14)) {
        throw new IllegalStateException("PROGRAMME_COUNTER has not been filled");
      }

      if (!filled.get(15)) {
        throw new IllegalStateException("PUSH_FUNNEL_BIT has not been filled");
      }

      if (!filled.get(16)) {
        throw new IllegalStateException("PUSH_PARAMETER has not been filled");
      }

      if (!filled.get(17)) {
        throw new IllegalStateException("PUSH_VALUE_ACC has not been filled");
      }

      if (!filled.get(18)) {
        throw new IllegalStateException("PUSH_VALUE_HIGH has not been filled");
      }

      if (!filled.get(19)) {
        throw new IllegalStateException("PUSH_VALUE_LOW has not been filled");
      }

      if (!filled.get(20)) {
        throw new IllegalStateException("VALID_JUMP_DESTINATION has not been filled");
      }

      filled.clear();

      TraceWriter.writeTrace(moduleName, formattedDate, build());

      return this;
    }

    public TraceBuilder fillAndValidateRow() {
      if (!filled.get(0)) {
        acc=BigInteger.ZERO;
        this.filled.set(0);
      }
      if (!filled.get(2)) {
        codeFragmentIndex= BigInteger.ZERO;
        this.filled.set(2);
      }
      if (!filled.get(3)) {
        codeFragmentIndexInfty= BigInteger.ZERO;
        this.filled.set(3);
      }
      if (!filled.get(4)) {
        codeSize= BigInteger.ZERO;
        this.filled.set(4);
      }
      if (!filled.get(1)) {
        codesizeReached = false;
        this.filled.set(1);
      }
      if (!filled.get(5)) {
        counter= BigInteger.ZERO;
        this.filled.set(5);
      }
      if (!filled.get(6)) {
        counterMax= BigInteger.ZERO;
        this.filled.set(6);
      }
      if (!filled.get(7)) {
        counterPush= BigInteger.ZERO;
        this.filled.set(7);
      }
      if (!filled.get(8)) {
        index= BigInteger.ZERO;
        this.filled.set(8);
      }
      if (!filled.get(9)) {
        isPush = false;
        this.filled.set(9);
      }
      if (!filled.get(10)) {
        isPushData = false;
        this.filled.set(10);
      }
      if (!filled.get(11)) {
        limb = BigInteger.ZERO;
        this.filled.set(11);
      }
      if (!filled.get(21)) {
        nBytes= BigInteger.ZERO;
        this.filled.set(21);
      }
      if (!filled.get(22)) {
        nBytesAcc= BigInteger.ZERO;
        this.filled.set(22);
      }
      if (!filled.get(12)) {
        opcode = UnsignedByte.of(0);
        this.filled.set(12);
      }
      if (!filled.get(13)) {
        paddedBytecodeByte = UnsignedByte.of(0);
        this.filled.set(13);
      }
      if (!filled.get(14)) {
        programmeCounter= BigInteger.ZERO;
        this.filled.set(14);
      }
      if (!filled.get(15)) {
        pushFunnelBit = false;
        this.filled.set(15);
      }
      if (!filled.get(16)) {
        pushParameter= BigInteger.ZERO;
        this.filled.set(16);
      }
      if (!filled.get(17)) {
        pushValueAcc= BigInteger.ZERO;
        this.filled.set(17);
      }
      if (!filled.get(18)) {
        pushValueHigh= BigInteger.ZERO;
        this.filled.set(18);
      }
      if (!filled.get(19)) {
        pushValueLow= BigInteger.ZERO;
        this.filled.set(19);
      }
      if (!filled.get(20)) {
        validJumpDestination = false;
        this.filled.set(20);
      }

      return this.validateRow();
    }

    public Trace build() {
      if (!filled.isEmpty()) {
        throw new IllegalStateException("Cannot build trace with a non-validated row.");
      }

      return new Trace(
              lineCounter,
          acc,
          codeFragmentIndex,
          codeFragmentIndexInfty,
          codeSize,
          codesizeReached,
          counter,
          counterMax,
          counterPush,
          index,
          isPush,
          isPushData,
          limb,
          nBytes,
          nBytesAcc,
          opcode,
          paddedBytecodeByte,
          programmeCounter,
          pushFunnelBit,
          pushParameter,
          pushValueAcc,
          pushValueHigh,
          pushValueLow,
          validJumpDestination);
    }
  }
}
