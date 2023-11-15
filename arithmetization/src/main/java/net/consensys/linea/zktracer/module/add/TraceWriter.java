package net.consensys.linea.zktracer.module.add;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.tuweni.bytes.Bytes;

public class TraceWriter {

  public static BufferedWriter acc1Buffer;
  public static BufferedWriter acc2Buffer;
  public static BufferedWriter arg1HiBuffer;
  public static BufferedWriter arg1LoBuffer;
  public static BufferedWriter arg2HiBuffer;
  public static BufferedWriter arg2LoBuffer;
  public static BufferedWriter byte1Buffer;
  public static BufferedWriter byte2Buffer;
  public static BufferedWriter ctBuffer;
  public static BufferedWriter instBuffer;
  public static BufferedWriter overflowBuffer;
  public static BufferedWriter resHiBuffer;
  public static BufferedWriter resLoBuffer;
  public static BufferedWriter stampBuffer;

  public static int acc1BufferCounter;
  public static int acc2BufferCounter;
  public static int arg1HiBufferCounter;
  public static int arg1LoBufferCounter;
  public static int arg2HiBufferCounter;
  public static int arg2LoBufferCounter;
  public static int ctBufferCounter;
  public static int instBufferCounter;
  public static int resHiBufferCounter;
  public static int resLoBufferCounter;
  public static int stampBufferCounter;

  public static void init(final String moduleName, final String formattedDate) {
    try {
      acc1BufferCounter = 0;

      acc2BufferCounter = 0;

      arg1HiBufferCounter = 0;

      arg1LoBufferCounter = 0;

      arg2HiBufferCounter = 0;

      arg2LoBufferCounter = 0;

      ctBufferCounter = 0;

      instBufferCounter = 0;

      resHiBufferCounter = 0;

      resLoBufferCounter = 0;

      stampBufferCounter = 0;

      acc1Buffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "acc1.txt")));
      acc2Buffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "acc2.txt")));
      arg1HiBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "arg1Hi.txt")));
      arg1LoBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "arg1Lo.txt")));
      arg2HiBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "arg2Hi.txt")));
      arg2LoBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "arg2Lo.txt")));
      byte1Buffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "byte1.txt")));
      byte2Buffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "byte2.txt")));
      ctBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "ct.txt")));
      instBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "inst.txt")));
      overflowBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "overflow.txt")));
      resHiBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "resHi.txt")));
      resLoBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "resLo.txt")));
      stampBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "stamp.txt")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeTrace(final Trace traceLine) {

    try {
      if (!traceLine.getAcc1().equals(BigInteger.ZERO)) {
        if (acc1BufferCounter != 0) {
          acc1Buffer.write(acc1BufferCounter);
          acc1Buffer.write(' ');
        }
        acc1Buffer.write(Bytes.wrap(traceLine.getAcc1().toByteArray()).toShortHexString());
        acc1Buffer.write(' ');
        acc1BufferCounter = 0;
      } else {
        acc1BufferCounter++;
      }

      if (!traceLine.getAcc2().equals(BigInteger.ZERO)) {
        if (acc2BufferCounter != 0) {
          acc2Buffer.write(acc2BufferCounter);
          acc2Buffer.write(' ');
        }
        acc2Buffer.write(Bytes.wrap(traceLine.getAcc2().toByteArray()).toShortHexString());
        acc2Buffer.write(' ');
        acc2BufferCounter = 0;
      } else {
        acc2BufferCounter++;
      }

      if (!traceLine.getArg1Hi().equals(BigInteger.ZERO)) {
        if (arg1HiBufferCounter != 0) {
          arg1HiBuffer.write(arg1HiBufferCounter);
          arg1HiBuffer.write(' ');
        }
        arg1HiBuffer.write(Bytes.wrap(traceLine.getArg1Hi().toByteArray()).toShortHexString());
        arg1HiBuffer.write(' ');
        arg1HiBufferCounter = 0;
      } else {
        arg1HiBufferCounter++;
      }

      if (!traceLine.getArg1Lo().equals(BigInteger.ZERO)) {
        if (arg1LoBufferCounter != 0) {
          arg1LoBuffer.write(arg1LoBufferCounter);
          arg1LoBuffer.write(' ');
        }
        arg1LoBuffer.write(Bytes.wrap(traceLine.getArg1Lo().toByteArray()).toShortHexString());
        arg1LoBuffer.write(' ');
        arg1LoBufferCounter = 0;
      } else {
        arg1LoBufferCounter++;
      }

      if (!traceLine.getArg2Hi().equals(BigInteger.ZERO)) {
        if (arg2HiBufferCounter != 0) {
          arg2HiBuffer.write(arg2HiBufferCounter);
          arg2HiBuffer.write(' ');
        }
        arg2HiBuffer.write(Bytes.wrap(traceLine.getArg2Hi().toByteArray()).toShortHexString());
        arg2HiBuffer.write(' ');
        arg2HiBufferCounter = 0;
      } else {
        arg2HiBufferCounter++;
      }

      if (!traceLine.getArg2Lo().equals(BigInteger.ZERO)) {
        if (arg2LoBufferCounter != 0) {
          arg2LoBuffer.write(arg2LoBufferCounter);
          arg2LoBuffer.write(' ');
        }
        arg2LoBuffer.write(Bytes.wrap(traceLine.getArg2Lo().toByteArray()).toShortHexString());
        arg2LoBuffer.write(' ');
        arg2LoBufferCounter = 0;
      } else {
        arg2LoBufferCounter++;
      }

      byte1Buffer.write(
          Bytes.wrap(traceLine.getByte1().toBigInteger().toByteArray()).toShortHexString());

      byte2Buffer.write(
          Bytes.wrap(traceLine.getByte2().toBigInteger().toByteArray()).toShortHexString());

      if (!traceLine.getCt().equals(BigInteger.ZERO)) {
        if (ctBufferCounter != 0) {
          ctBuffer.write(ctBufferCounter);
          ctBuffer.write(' ');
        }
        ctBuffer.write(Bytes.wrap(traceLine.getCt().toByteArray()).toShortHexString());
        ctBuffer.write(' ');
        ctBufferCounter = 0;
      } else {
        ctBufferCounter++;
      }

      if (!traceLine.getInst().equals(BigInteger.ZERO)) {
        if (instBufferCounter != 0) {
          instBuffer.write(instBufferCounter);
          instBuffer.write(' ');
        }
        instBuffer.write(Bytes.wrap(traceLine.getInst().toByteArray()).toShortHexString());
        instBuffer.write(' ');
        instBufferCounter = 0;
      } else {
        instBufferCounter++;
      }

      overflowBuffer.write(
          (traceLine.getOverflow()
              ? Bytes.of((byte) 0x01).toShortHexString()
              : Bytes.of((byte) 0x00).toShortHexString()));

      if (!traceLine.getResHi().equals(BigInteger.ZERO)) {
        if (resHiBufferCounter != 0) {
          resHiBuffer.write(resHiBufferCounter);
          resHiBuffer.write(' ');
        }
        resHiBuffer.write(Bytes.wrap(traceLine.getResHi().toByteArray()).toShortHexString());
        resHiBuffer.write(' ');
        resHiBufferCounter = 0;
      } else {
        resHiBufferCounter++;
      }

      if (!traceLine.getResLo().equals(BigInteger.ZERO)) {
        if (resLoBufferCounter != 0) {
          resLoBuffer.write(resLoBufferCounter);
          resLoBuffer.write(' ');
        }
        resLoBuffer.write(Bytes.wrap(traceLine.getResLo().toByteArray()).toShortHexString());
        resLoBuffer.write(' ');
        resLoBufferCounter = 0;
      } else {
        resLoBufferCounter++;
      }

      if (!traceLine.getStamp().equals(BigInteger.ZERO)) {
        if (stampBufferCounter != 0) {
          stampBuffer.write(stampBufferCounter);
          stampBuffer.write(' ');
        }
        stampBuffer.write(Bytes.wrap(traceLine.getStamp().toByteArray()).toShortHexString());
        stampBuffer.write(' ');
        stampBufferCounter = 0;
      } else {
        stampBufferCounter++;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void flush() {
    try {
      acc1Buffer.close();
      acc2Buffer.close();
      arg1HiBuffer.close();
      arg1LoBuffer.close();
      arg2HiBuffer.close();
      arg2LoBuffer.close();
      byte1Buffer.close();
      byte2Buffer.close();
      ctBuffer.close();
      instBuffer.close();
      overflowBuffer.close();
      resHiBuffer.close();
      resLoBuffer.close();
      stampBuffer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
