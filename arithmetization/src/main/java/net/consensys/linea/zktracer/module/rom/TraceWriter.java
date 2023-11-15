package net.consensys.linea.zktracer.module.rom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.tuweni.bytes.Bytes;

public class TraceWriter {
  public static BufferedWriter accBuffer;
  public static BufferedWriter codeFragmentIndexBuffer;
  public static BufferedWriter codeFragmentIndexInftyBuffer;
  public static BufferedWriter codeSizeBuffer;
  public static BufferedWriter codesizeReachedBuffer;
  public static BufferedWriter counterBuffer;
  public static BufferedWriter counterMaxBuffer;
  public static BufferedWriter counterPushButton;
  public static BufferedWriter indexBuffer;
  public static BufferedWriter isPushButton;
  public static BufferedWriter isPushDataBuffer;
  public static BufferedWriter limbBuffer;
  public static BufferedWriter nBytesBuffer;
  public static BufferedWriter nBytesAccBuffer;
  public static BufferedWriter opcodeBuffer;
  public static BufferedWriter paddedBytecodeByteBuffer;
  public static BufferedWriter programmeCounterBuffer;
  public static BufferedWriter pushFunnelBitBuffer;
  public static BufferedWriter pushParameterBuffer;
  public static BufferedWriter pushValueAccBuffer;
  public static BufferedWriter pushValueHighBuffer;
  public static BufferedWriter pushValueLowBuffer;
  public static BufferedWriter validJumpDestinationBuffer;

  public static int accBufferCounter;

  public static int codeFragmentIndexBufferCounter;

  public static int codeFragmentIndexInftyBufferCounter;

  public static int codeSizeBufferCounter;

  public static int codesizeReachedBufferCounter;

  public static int counterBufferCounter;

  public static int counterMaxBufferCounter;

  public static int counterPushButtonCounter;

  public static int indexBufferCounter;

  public static int limbBufferCounter;

  public static int nBytesBufferCounter;

  public static int nBytesAccBufferCounter;

  public static int opcodeBufferCounter;

  public static int paddedBytecodeByteBufferCounter;

  public static int programmeCounterBufferCounter;

  public static int pushFunnelBitBufferCounter;

  public static int pushParameterBufferCounter;

  public static int pushValueAccBufferCounter;

  public static int pushValueHighBufferCounter;

  public static int pushValueLowBufferCounter;

  public static void init(final String moduleName, final String formattedDate) {
    try {
      accBufferCounter = 0;

      codeFragmentIndexBufferCounter = 0;

      codeFragmentIndexInftyBufferCounter = 0;

      codeSizeBufferCounter = 0;

      codesizeReachedBufferCounter = 0;

      counterBufferCounter = 0;

      counterMaxBufferCounter = 0;

      counterPushButtonCounter = 0;

      indexBufferCounter = 0;

      limbBufferCounter = 0;

      nBytesBufferCounter = 0;

      nBytesAccBufferCounter = 0;

      opcodeBufferCounter = 0;

      paddedBytecodeByteBufferCounter = 0;

      programmeCounterBufferCounter = 0;

      pushFunnelBitBufferCounter = 0;

      pushParameterBufferCounter = 0;

      pushValueHighBufferCounter = 0;

      accBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "acc.txt")));
      codeFragmentIndexBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "codeFragmentIndex.txt")));
      codeFragmentIndexInftyBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "codeFragmentIndexInfty.txt")));
      codeSizeBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "codeSize.txt")));
      codesizeReachedBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "codesizeReached.txt")));
      counterBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "counter.txt")));
      counterMaxBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "counterMax.txt")));
      counterPushButton =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "counterPush.txt")));
      indexBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "index.txt")));
      isPushButton =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "isPush.txt")));
      isPushDataBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "isPushData.txt")));
      limbBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "limb.txt")));
      nBytesBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "nBytes.txt")));
      nBytesAccBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "nBytesAcc.txt")));
      opcodeBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "opcode.txt")));
      paddedBytecodeByteBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "paddedBytecodeByte.txt")));
      programmeCounterBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "programmeCounter.txt")));
      pushFunnelBitBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "pushFunnelBit.txt")));
      pushParameterBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "pushParameter.txt")));
      pushValueAccBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "pushValueAcc.txt")));
      pushValueHighBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "pushValueHigh.txt")));
      pushValueLowBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "pushValueLow.txt")));
      validJumpDestinationBuffer =
          new BufferedWriter(
              new FileWriter(
                  "/data/traces/%s/%s-%s"
                      .formatted(moduleName, formattedDate, "validJumpDestination.txt")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeTrace(final Trace traceLine) {

    try {
      if (!traceLine.getAcc().equals(BigInteger.ZERO)) {
        if (accBufferCounter != 0) {
          accBuffer.write(accBufferCounter);
          accBuffer.write(' ');
        }
        accBuffer.write(Bytes.wrap(traceLine.getAcc().toByteArray()).toShortHexString());
        accBuffer.write(' ');
        accBufferCounter = 0;
      } else {
        accBufferCounter++;
      }

      if (!traceLine.getCodeFragmentIndex().equals(BigInteger.ZERO)) {
        if (codeFragmentIndexBufferCounter != 0) {
          codeFragmentIndexBuffer.write(codeFragmentIndexBufferCounter);
          codeFragmentIndexBuffer.write(' ');
        }
        codeFragmentIndexBuffer.write(
            Bytes.wrap(traceLine.getCodeFragmentIndex().toByteArray()).toShortHexString());
        codeFragmentIndexBuffer.write(' ');
        codeFragmentIndexBufferCounter = 0;
      } else {
        codeFragmentIndexBufferCounter++;
      }

      if (!traceLine.getCodeFragmentIndexInfty().equals(BigInteger.ZERO)) {
        if (codeFragmentIndexInftyBufferCounter != 0) {
          codeFragmentIndexInftyBuffer.write(codeFragmentIndexInftyBufferCounter);
          codeFragmentIndexInftyBuffer.write(' ');
        }
        codeFragmentIndexInftyBuffer.write(
            Bytes.wrap(traceLine.getCodeFragmentIndexInfty().toByteArray()).toShortHexString());
        codeFragmentIndexInftyBuffer.write(' ');
        codeFragmentIndexInftyBufferCounter = 0;
      } else {
        codeFragmentIndexInftyBufferCounter++;
      }

      if (!traceLine.getCodeSize().equals(BigInteger.ZERO)) {
        if (codeSizeBufferCounter != 0) {
          codeSizeBuffer.write(codeSizeBufferCounter);
          codeSizeBuffer.write(' ');
        }
        codeSizeBuffer.write(Bytes.wrap(traceLine.getCodeSize().toByteArray()).toShortHexString());
        codeSizeBuffer.write(' ');
        codeSizeBufferCounter = 0;
      } else {
        codeSizeBufferCounter++;
      }

      codesizeReachedBuffer.write((traceLine.getCodesizeReached() ? 1 : 0));
      codesizeReachedBuffer.write(' ');

      if (!traceLine.getCounter().equals(BigInteger.ZERO)) {
        if (counterBufferCounter != 0) {
          counterBuffer.write(counterBufferCounter);
          counterBuffer.write(' ');
        }
        counterBuffer.write(Bytes.wrap(traceLine.getCounter().toByteArray()).toShortHexString());
        counterBuffer.write(' ');
        counterBufferCounter = 0;
      } else {
        counterBufferCounter++;
      }

      if (!traceLine.getCounterMax().equals(BigInteger.ZERO)) {
        if (counterMaxBufferCounter != 0) {
          counterMaxBuffer.write(counterMaxBufferCounter);
          counterMaxBuffer.write(' ');
        }
        counterMaxBuffer.write(
            Bytes.wrap(traceLine.getCounterMax().toByteArray()).toShortHexString());
        counterMaxBuffer.write(' ');
        counterMaxBufferCounter = 0;
      } else {
        counterMaxBufferCounter++;
      }

      if (!traceLine.getCounterPush().equals(BigInteger.ZERO)) {
        if (counterPushButtonCounter != 0) {
          counterPushButton.write(counterPushButtonCounter);
          counterPushButton.write(' ');
        }
        counterPushButton.write(
            Bytes.wrap(traceLine.getCounterPush().toByteArray()).toShortHexString());
        counterPushButton.write(' ');
        counterPushButtonCounter = 0;
      } else {
        counterPushButtonCounter++;
      }

      if (!traceLine.getIndex().equals(BigInteger.ZERO)) {
        if (indexBufferCounter != 0) {
          indexBuffer.write(indexBufferCounter);
          indexBuffer.write(' ');
        }
        indexBuffer.write(Bytes.wrap(traceLine.getIndex().toByteArray()).toShortHexString());
        indexBuffer.write(' ');
        indexBufferCounter = 0;
      } else {
        indexBufferCounter++;
      }

      isPushButton.write((traceLine.getPush() ? 1 : 0));
      isPushButton.write(' ');

      isPushDataBuffer.write((traceLine.getPushData() ? 1 : 0));
      isPushDataBuffer.write(' ');

      if (!traceLine.getLimb().equals(BigInteger.ZERO)) {
        if (limbBufferCounter != 0) {
          limbBuffer.write(limbBufferCounter);
          limbBuffer.write(' ');
        }
        limbBuffer.write(Bytes.wrap(traceLine.getLimb().toByteArray()).toShortHexString());
        limbBuffer.write(' ');
        limbBufferCounter = 0;
      } else {
        limbBufferCounter++;
      }

      if (!traceLine.getnBytes().equals(BigInteger.ZERO)) {
        if (nBytesBufferCounter != 0) {
          nBytesBuffer.write(nBytesBufferCounter);
          nBytesBuffer.write(' ');
        }
        nBytesBuffer.write(
            Bytes.wrap(traceLine.getnBytes().toByteArray()).toShortHexString() + "\n");
        nBytesBufferCounter = 0;
      } else {
        nBytesBufferCounter++;
      }

      if (!traceLine.getnBytesAcc().equals(BigInteger.ZERO)) {
        if (nBytesAccBufferCounter != 0) {
          nBytesAccBuffer.write(nBytesAccBufferCounter);
          nBytesAccBuffer.write(' ');
        }
        nBytesAccBuffer.write(
            Bytes.wrap(traceLine.getnBytesAcc().toByteArray()).toShortHexString() + "\n");
        nBytesAccBufferCounter = 0;
      } else {
        nBytesAccBufferCounter++;
      }

      opcodeBuffer.write(
          Bytes.wrap(traceLine.getOpcode().toBigInteger().toByteArray()).toShortHexString());
      opcodeBuffer.write(' ');

      paddedBytecodeByteBuffer.write(
          Bytes.wrap(traceLine.getPaddedBytecodeByte().toBigInteger().toByteArray())
              .toShortHexString());
      paddedBytecodeByteBuffer.write(' ');

      if (!traceLine.getProgrammeCounter().equals(BigInteger.ZERO)) {
        if (programmeCounterBufferCounter != 0) {
          programmeCounterBuffer.write(programmeCounterBufferCounter);
          programmeCounterBuffer.write(' ');
        }
        programmeCounterBuffer.write(
            Bytes.wrap(traceLine.getProgrammeCounter().toByteArray()).toShortHexString());
        programmeCounterBuffer.write(' ');
        programmeCounterBufferCounter = 0;
      } else {
        programmeCounterBufferCounter++;
      }

      pushFunnelBitBuffer.write((traceLine.getPushFunnelBit() ? 1 : 0));
      pushFunnelBitBuffer.write(' ');

      if (!traceLine.getPushParameter().equals(BigInteger.ZERO)) {
        if (pushParameterBufferCounter != 0) {
          pushParameterBuffer.write(pushParameterBufferCounter);
          pushParameterBuffer.write(' ');
        }
        pushParameterBuffer.write(
            Bytes.wrap(traceLine.getPushParameter().toByteArray()).toShortHexString());
        pushParameterBuffer.write(' ');
        pushParameterBufferCounter = 0;
      } else {
        pushParameterBufferCounter++;
      }

      if (!traceLine.getPushValueAcc().equals(BigInteger.ZERO)) {
        if (pushValueAccBufferCounter != 0) {
          pushValueAccBuffer.write(pushValueAccBufferCounter);
          pushValueAccBuffer.write(' ');
        }
        pushValueAccBuffer.write(
            Bytes.wrap(traceLine.getPushValueAcc().toByteArray()).toShortHexString());
        pushValueAccBuffer.write(' ');
        pushValueAccBufferCounter = 0;
      } else {
        pushValueAccBufferCounter++;
      }

      if (!traceLine.getPushValueHigh().equals(BigInteger.ZERO)) {
        if (pushValueHighBufferCounter != 0) {
          pushValueHighBuffer.write(pushValueHighBufferCounter);
          pushValueHighBuffer.write(' ');
        }
        pushValueHighBuffer.write(
            Bytes.wrap(traceLine.getPushValueHigh().toByteArray()).toShortHexString());
        pushValueHighBuffer.write(' ');
        pushValueHighBufferCounter = 0;
      } else {
        pushValueHighBufferCounter++;
      }

      if (!traceLine.getPushValueLow().equals(BigInteger.ZERO)) {
        if (pushValueLowBufferCounter != 0) {
          pushValueLowBuffer.write(pushValueLowBufferCounter);
          pushValueLowBuffer.write(' ');
        }
        pushValueLowBuffer.write(
            Bytes.wrap(traceLine.getPushValueLow().toByteArray()).toShortHexString());
        pushValueLowBuffer.write(' ');
        pushValueLowBufferCounter = 0;
      } else {
        pushValueLowBufferCounter++;
      }

      validJumpDestinationBuffer.write((traceLine.getValidJumpDestination() ? 1 : 0));
      validJumpDestinationBuffer.write(' ');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void flush() {
    try {
      accBuffer.close();
      codeFragmentIndexBuffer.close();
      codeFragmentIndexInftyBuffer.close();
      codeSizeBuffer.close();
      codesizeReachedBuffer.close();
      counterBuffer.close();
      counterMaxBuffer.close();
      counterPushButton.close();
      indexBuffer.close();
      isPushButton.close();
      isPushDataBuffer.close();
      limbBuffer.close();
      nBytesBuffer.close();
      nBytesAccBuffer.close();
      opcodeBuffer.close();
      paddedBytecodeByteBuffer.close();
      programmeCounterBuffer.close();
      pushFunnelBitBuffer.close();
      pushParameterBuffer.close();
      pushValueAccBuffer.close();
      pushValueHighBuffer.close();
      pushValueLowBuffer.close();
      validJumpDestinationBuffer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
