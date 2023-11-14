package net.consensys.linea.zktracer.module.add;

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
        acc1Buffer.write(counter + " " + traceLine.getAcc1().toString() + "\n");
      }
      if (traceLine.getAcc2() != null) {
        acc2Buffer.write(counter + " " + traceLine.getAcc2().toString() + "\n");
      }
      if (traceLine.getArg1Hi() != null) {
        arg1HiBuffer.write(counter + " " + traceLine.getArg1Hi().toString() + "\n");
      }
      if (traceLine.getArg1Lo() != null) {
        arg1LoBuffer.write(counter + " " + traceLine.getArg1Lo().toString() + "\n");
      }
      if (traceLine.getArg2Hi() != null) {
        arg2HiBuffer.write(counter + " " + traceLine.getArg2Hi().toString() + "\n");
      }
      if (traceLine.getByte1() != null) {
        byte1Buffer.write(counter + " " + traceLine.getByte1().toString() + "\n");
      }
      if (traceLine.getByte2() != null) {
        byte2Buffer.write(counter + " " + traceLine.getByte2().toString() + "\n");
      }
      if (traceLine.getCt() != null) {
        ctBuffer.write(counter + " " + traceLine.getCt().toString() + "\n");
      }
      if (traceLine.getInst() != null) {
        instBuffer.write(counter + " " + traceLine.getInst().toString() + "\n");
      }
      if (traceLine.getOverflow() != null) {
        overflowBuffer.write(counter + " " + traceLine.getOverflow().toString() + "\n");
      }
      if (traceLine.getResHi() != null) {
        resHiBuffer.write(counter + " " + traceLine.getResHi().toString() + "\n");
      }
      if (traceLine.getResLo() != null) {
        resLoBuffer.write(counter + " " + traceLine.getResLo().toString() + "\n");
      }
      if (traceLine.getStamp() != null) {
        stampBuffer.write(counter + " " + traceLine.getStamp().toString() + "\n");
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
