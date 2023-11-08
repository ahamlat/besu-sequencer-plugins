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

package net.consensys.linea.zktracer.module.add;

import java.io.File;
import java.math.BigInteger;
import java.util.BitSet;

import net.consensys.linea.zktracer.types.UnsignedByte;

/**
 * WARNING: This code is generated automatically. Any modifications to this code may be overwritten
 * and could lead to unexpected behavior. Please DO NOT ATTEMPT TO MODIFY this code directly.
 */
public record Trace(
    int lineCounter,
    BigInteger acc1,
    BigInteger acc2,
    BigInteger arg1Hi,
    BigInteger arg1Lo,
    BigInteger arg2Hi,
    BigInteger arg2Lo,
    UnsignedByte byte1,
    UnsignedByte byte2,
    BigInteger ct,
    BigInteger inst,
    Boolean overflow,
    BigInteger resHi,
    BigInteger resLo,
    BigInteger stamp) {
  static TraceBuilder builder() {
    return new TraceBuilder("add");
  }

  public int size() {
    return lineCounter;
  }

  static class TraceBuilder {
    private final BitSet filled = new BitSet();
    private final String moduleName;

    private int lineCounter = 0;
    private BigInteger acc1;
    private BigInteger acc2;
    private BigInteger arg1Hi;
    private BigInteger arg1Lo;
    private BigInteger arg2Hi;
    private BigInteger arg2Lo;
    private UnsignedByte byte1;
    private UnsignedByte byte2;
    private BigInteger ct;
    private BigInteger inst;
    private Boolean overflow;
    private BigInteger resHi;
    private BigInteger resLo;
    private BigInteger stamp;

    public TraceBuilder(final String moduleName) {
      this.moduleName = moduleName;
    }

    public int size() {
      if (!filled.isEmpty()) {
        throw new RuntimeException("Cannot measure a trace with a non-validated row.");
      }

      return lineCounter;
    }

    public TraceBuilder acc1(final BigInteger b) {
      if (filled.get(0)) {
        throw new IllegalStateException("ACC_1 already set");
      } else {
        filled.set(0);
      }

      acc1 = b;
      lineCounter++;
      return this;
    }

    public TraceBuilder acc2(final BigInteger b) {
      if (filled.get(1)) {
        throw new IllegalStateException("ACC_2 already set");
      } else {
        filled.set(1);
      }

      acc2 = b;

      return this;
    }

    public TraceBuilder arg1Hi(final BigInteger b) {
      if (filled.get(2)) {
        throw new IllegalStateException("ARG_1_HI already set");
      } else {
        filled.set(2);
      }

      arg1Hi = b;

      return this;
    }

    public TraceBuilder arg1Lo(final BigInteger b) {
      if (filled.get(3)) {
        throw new IllegalStateException("ARG_1_LO already set");
      } else {
        filled.set(3);
      }

      arg1Lo = b;

      return this;
    }

    public TraceBuilder arg2Hi(final BigInteger b) {
      if (filled.get(4)) {
        throw new IllegalStateException("ARG_2_HI already set");
      } else {
        filled.set(4);
      }

      arg2Hi = b;

      return this;
    }

    public TraceBuilder arg2Lo(final BigInteger b) {
      if (filled.get(5)) {
        throw new IllegalStateException("ARG_2_LO already set");
      } else {
        filled.set(5);
      }

      arg2Lo = b;

      return this;
    }

    public TraceBuilder byte1(final UnsignedByte b) {
      if (filled.get(6)) {
        throw new IllegalStateException("BYTE_1 already set");
      } else {
        filled.set(6);
      }

      byte1 = b;

      return this;
    }

    public TraceBuilder byte2(final UnsignedByte b) {
      if (filled.get(7)) {
        throw new IllegalStateException("BYTE_2 already set");
      } else {
        filled.set(7);
      }

      byte2 = b;

      return this;
    }

    public TraceBuilder ct(final BigInteger b) {
      if (filled.get(8)) {
        throw new IllegalStateException("CT already set");
      } else {
        filled.set(8);
      }

      ct = b;

      return this;
    }

    public TraceBuilder inst(final BigInteger b) {
      if (filled.get(9)) {
        throw new IllegalStateException("INST already set");
      } else {
        filled.set(9);
      }

      inst = b;

      return this;
    }

    public TraceBuilder overflow(final Boolean b) {
      if (filled.get(10)) {
        throw new IllegalStateException("OVERFLOW already set");
      } else {
        filled.set(10);
      }

      overflow = b;

      return this;
    }

    public TraceBuilder resHi(final BigInteger b) {
      if (filled.get(11)) {
        throw new IllegalStateException("RES_HI already set");
      } else {
        filled.set(11);
      }

      resHi = b;

      return this;
    }

    public TraceBuilder resLo(final BigInteger b) {
      if (filled.get(12)) {
        throw new IllegalStateException("RES_LO already set");
      } else {
        filled.set(12);
      }

      resLo = b;

      return this;
    }

    public TraceBuilder stamp(final BigInteger b) {
      if (filled.get(13)) {
        throw new IllegalStateException("STAMP already set");
      } else {
        filled.set(13);
      }

      stamp = b;

      return this;
    }

    public TraceBuilder validateRow() {
      if (!filled.get(0)) {
        throw new IllegalStateException("ACC_1 has not been filled");
      }

      if (!filled.get(1)) {
        throw new IllegalStateException("ACC_2 has not been filled");
      }

      if (!filled.get(2)) {
        throw new IllegalStateException("ARG_1_HI has not been filled");
      }

      if (!filled.get(3)) {
        throw new IllegalStateException("ARG_1_LO has not been filled");
      }

      if (!filled.get(4)) {
        throw new IllegalStateException("ARG_2_HI has not been filled");
      }

      if (!filled.get(5)) {
        throw new IllegalStateException("ARG_2_LO has not been filled");
      }

      if (!filled.get(6)) {
        throw new IllegalStateException("BYTE_1 has not been filled");
      }

      if (!filled.get(7)) {
        throw new IllegalStateException("BYTE_2 has not been filled");
      }

      if (!filled.get(8)) {
        throw new IllegalStateException("CT has not been filled");
      }

      if (!filled.get(9)) {
        throw new IllegalStateException("INST has not been filled");
      }

      if (!filled.get(10)) {
        throw new IllegalStateException("OVERFLOW has not been filled");
      }

      if (!filled.get(11)) {
        throw new IllegalStateException("RES_HI has not been filled");
      }

      if (!filled.get(12)) {
        throw new IllegalStateException("RES_LO has not been filled");
      }

      if (!filled.get(13)) {
        throw new IllegalStateException("STAMP has not been filled");
      }

      filled.clear();

      TraceWriter.writeTrace(moduleName, build());

      return this;
    }

    public TraceBuilder fillAndValidateRow() {
      if (!filled.get(0)) {
        acc1 = BigInteger.ZERO;
        this.filled.set(0);
      }
      if (!filled.get(1)) {
        acc2 = BigInteger.ZERO;
        this.filled.set(1);
      }
      if (!filled.get(2)) {
        arg1Hi = BigInteger.ZERO;
        this.filled.set(2);
      }
      if (!filled.get(3)) {
        arg1Lo = BigInteger.ZERO;
        this.filled.set(3);
      }
      if (!filled.get(4)) {
        arg2Hi = BigInteger.ZERO;
        this.filled.set(4);
      }
      if (!filled.get(5)) {
        arg2Lo = BigInteger.ZERO;
        this.filled.set(5);
      }
      if (!filled.get(6)) {
        byte1 = UnsignedByte.of(0);
        this.filled.set(6);
      }
      if (!filled.get(7)) {
        byte2 = UnsignedByte.of(0);
        this.filled.set(7);
      }
      if (!filled.get(8)) {
        ct = BigInteger.ZERO;
        this.filled.set(8);
      }
      if (!filled.get(9)) {
        inst = BigInteger.ZERO;
        this.filled.set(9);
      }
      if (!filled.get(10)) {
        overflow = false;
        this.filled.set(10);
      }
      if (!filled.get(11)) {
        resHi = BigInteger.ZERO;
        this.filled.set(11);
      }
      if (!filled.get(12)) {
        resLo = BigInteger.ZERO;
        this.filled.set(12);
      }
      if (!filled.get(13)) {
        stamp = BigInteger.ZERO;
        this.filled.set(13);
      }

      return this.validateRow();
    }

    public Trace build() {
      if (!filled.isEmpty()) {
        throw new IllegalStateException("Cannot build trace with a non-validated row.");
      }

      return new Trace(
          lineCounter,
          acc1,
          acc2,
          arg1Hi,
          arg1Lo,
          arg2Hi,
          arg2Lo,
          byte1,
          byte2,
          ct,
          inst,
          overflow,
          resHi,
          resLo,
          stamp);
    }
  }
}
