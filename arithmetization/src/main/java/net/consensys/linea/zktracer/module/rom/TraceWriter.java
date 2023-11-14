package net.consensys.linea.zktracer.module.rom;

import org.apache.tuweni.bytes.Bytes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

public class TraceWriter {

    public static final Method[] DECLARED_METHODS = Trace.class.getDeclaredMethods();
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

    static int counter = 0;

    public static void init(final String moduleName, final String formattedDate) {
        try {
            accBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "acc.txt")));
            codeFragmentIndexBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "codeFragmentIndex.txt")));
            codeFragmentIndexInftyBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "codeFragmentIndexInfty.txt")));
            codeSizeBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "codeSize.txt")));
            codesizeReachedBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "codesizeReached.txt")));
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
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "paddedBytecodeByte.txt")));
            programmeCounterBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "programmeCounter.txt")));
            pushFunnelBitBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "pushFunnelBit.txt")));
            pushParameterBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "pushParameter.txt")));
            pushValueAccBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "pushValueAcc.txt")));
            pushValueHighBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "pushValueHigh.txt")));
            pushValueLowBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "pushValueLow.txt")));
            validJumpDestinationBuffer =
                    new BufferedWriter(
                            new FileWriter(
                                    "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, "validJumpDestination.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTrace(final Trace traceLine) {

        try {
            if (traceLine.getAcc() != null) {
                accBuffer.write(counter + " " + Bytes.wrap(traceLine.getAcc().toByteArray())
                        .toShortHexString() + "\n");
            }
            // Write other attributes here
            if (traceLine.getCodeFragmentIndex() != null) {
                codeFragmentIndexBuffer.write(counter + " " + Bytes.wrap(traceLine.getCodeFragmentIndex().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getCodeFragmentIndexInfty() != null) {
                codeFragmentIndexInftyBuffer.write(counter + " " + Bytes.wrap(traceLine.getCodeFragmentIndexInfty().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getCodeSize() != null) {
                codeSizeBuffer.write(counter + " " + Bytes.wrap(traceLine.getCodeSize().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getCodesizeReached() != null) {
                codesizeReachedBuffer.write(counter + " " +  (traceLine.getCodesizeReached()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");
            }
            if (traceLine.getCounter() != null) {
                counterBuffer.write(counter + " " + Bytes.wrap(traceLine.getCounter().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getCounterMax() != null) {
                counterMaxBuffer.write(counter + " " + Bytes.wrap(traceLine.getCounterMax().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getCounterPush() != null) {
                counterPushButton.write(counter + " " + Bytes.wrap(traceLine.getCounterPush().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getIndex() != null) {
                indexBuffer.write(counter + " " + Bytes.wrap(traceLine.getIndex().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getPush() != null) {
                isPushButton.write(counter + " " +  (traceLine.getPush()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");
            }
            if (traceLine.getPushData() != null) {
                isPushDataBuffer.write(counter + " " +  (traceLine.getPushData()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");
            }
            if (traceLine.getLimb() != null) {
                limbBuffer.write(counter + " " + Bytes.wrap(traceLine.getLimb().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getnBytes() != null) {
                nBytesBuffer.write(counter + " " + Bytes.wrap(traceLine.getnBytes().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getnBytesAcc() != null) {
                nBytesAccBuffer.write(counter + " " + Bytes.wrap(traceLine.getnBytesAcc().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getOpcode() != null) {
                opcodeBuffer.write(counter + " " + Bytes.wrap(traceLine.getOpcode().toBigInteger().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getPaddedBytecodeByte() != null) {
                paddedBytecodeByteBuffer.write(counter + " " + Bytes.wrap(traceLine.getPaddedBytecodeByte().toBigInteger().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getProgrammeCounter() != null) {
                programmeCounterBuffer.write(counter + " " + Bytes.wrap(traceLine.getProgrammeCounter().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getPushFunnelBit() != null) {
                pushFunnelBitBuffer.write(counter + " " +  (traceLine.getPushFunnelBit()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");
            }
            if (traceLine.getPushParameter() != null) {
                pushParameterBuffer.write(counter + " " + Bytes.wrap(traceLine.getPushParameter().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getPushValueAcc() != null) {
                pushValueAccBuffer.write(counter + " " + Bytes.wrap(traceLine.getPushValueAcc().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getPushValueHigh() != null) {
                pushValueHighBuffer.write(counter + " " + Bytes.wrap(traceLine.getPushValueHigh().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getPushValueLow() != null) {
                pushValueLowBuffer.write(counter + " " + Bytes.wrap(traceLine.getPushValueLow().toByteArray())
                        .toShortHexString() + "\n");
            }
            if (traceLine.getValidJumpDestination() != null) {
                validJumpDestinationBuffer.write(counter + " " +  (traceLine.getValidJumpDestination()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");
            }
            counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void flush() {
        counter = 0;
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