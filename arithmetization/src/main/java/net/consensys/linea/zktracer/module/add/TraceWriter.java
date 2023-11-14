package net.consensys.linea.zktracer.module.add;

import org.apache.tuweni.bytes.Bytes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

public class TraceWriter {

  public static final Method[] DECLARED_METHODS = Trace.class.getDeclaredMethods();
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

  static int counter = 0;

  public static void init(final String moduleName, final String formattedDate) {
    try {
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
      if (traceLine.getAcc1() != null) {
        acc1Buffer.write(counter + " " + Bytes.wrap(traceLine.getAcc1().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getAcc2() != null) {
        acc2Buffer.write(counter + " " + Bytes.wrap(traceLine.getAcc2().toByteArray())
                .toShortHexString()+ "\n");
      }
      if (traceLine.getArg1Hi() != null) {
        arg1HiBuffer.write(counter + " " + Bytes.wrap(traceLine.getArg1Hi().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getArg1Lo() != null) {
        arg1LoBuffer.write(counter + " " + Bytes.wrap(traceLine.getArg1Lo().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getArg2Hi() != null) {
        arg2HiBuffer.write(counter + " " + Bytes.wrap(traceLine.getArg2Hi().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getArg2Hi() != null) {
        arg2LoBuffer.write(counter + " " + Bytes.wrap(traceLine.getArg2Lo().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getByte1() != null) {
        byte1Buffer.write(counter + " " + Bytes.wrap(traceLine.getByte1().toBigInteger().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getByte2() != null) {
        byte2Buffer.write(counter + " " + Bytes.wrap(traceLine.getByte2().toBigInteger().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getCt() != null) {
        ctBuffer.write(counter + " " + Bytes.wrap(traceLine.getCt().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getInst() != null) {
        instBuffer.write(counter + " " + Bytes.wrap(traceLine.getInst().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getOverflow() != null) {
        overflowBuffer.write(counter + " " +  (traceLine.getOverflow()
                ? Bytes.of((byte) 0x01).toShortHexString()
                : Bytes.of((byte) 0x00).toShortHexString()) + "\n");
      }
      if (traceLine.getResHi() != null) {
        resHiBuffer.write(counter + " " + Bytes.wrap(traceLine.getResHi().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getResLo() != null) {
        resLoBuffer.write(counter + " " + Bytes.wrap(traceLine.getResLo().toByteArray())
                .toShortHexString() + "\n");
      }
      if (traceLine.getStamp() != null) {
        stampBuffer.write(counter + " " + Bytes.wrap(traceLine.getStamp().toByteArray())
                .toShortHexString() + "\n");
      }
      counter++;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void flush() {
    counter = 0;
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
