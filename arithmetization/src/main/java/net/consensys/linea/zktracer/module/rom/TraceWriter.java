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
                accBuffer.write(Bytes.wrap(traceLine.getAcc().toByteArray())
                        .toShortHexString() + "\n");

                codeFragmentIndexBuffer.write(Bytes.wrap(traceLine.getCodeFragmentIndex().toByteArray())
                        .toShortHexString() + "\n");

                codeFragmentIndexInftyBuffer.write(Bytes.wrap(traceLine.getCodeFragmentIndexInfty().toByteArray())
                        .toShortHexString() + "\n");

                codeSizeBuffer.write(Bytes.wrap(traceLine.getCodeSize().toByteArray())
                        .toShortHexString() + "\n");

                codesizeReachedBuffer.write( (traceLine.getCodesizeReached()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");

                counterBuffer.write(Bytes.wrap(traceLine.getCounter().toByteArray())
                        .toShortHexString() + "\n");

                counterMaxBuffer.write(Bytes.wrap(traceLine.getCounterMax().toByteArray())
                        .toShortHexString() + "\n");

                counterPushButton.write(Bytes.wrap(traceLine.getCounterPush().toByteArray())
                        .toShortHexString() + "\n");

                indexBuffer.write(Bytes.wrap(traceLine.getIndex().toByteArray())
                        .toShortHexString() + "\n");

                isPushButton.write( (traceLine.getPush()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");

                isPushDataBuffer.write( (traceLine.getPushData()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");

                limbBuffer.write(Bytes.wrap(traceLine.getLimb().toByteArray())
                        .toShortHexString() + "\n");

                nBytesBuffer.write(Bytes.wrap(traceLine.getnBytes().toByteArray())
                        .toShortHexString() + "\n");

                nBytesAccBuffer.write(Bytes.wrap(traceLine.getnBytesAcc().toByteArray())
                        .toShortHexString() + "\n");

                opcodeBuffer.write(Bytes.wrap(traceLine.getOpcode().toBigInteger().toByteArray())
                        .toShortHexString() + "\n");

                paddedBytecodeByteBuffer.write(Bytes.wrap(traceLine.getPaddedBytecodeByte().toBigInteger().toByteArray())
                        .toShortHexString() + "\n");

                programmeCounterBuffer.write(Bytes.wrap(traceLine.getProgrammeCounter().toByteArray())
                        .toShortHexString() + "\n");


                pushFunnelBitBuffer.write( (traceLine.getPushFunnelBit()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");


                pushParameterBuffer.write(Bytes.wrap(traceLine.getPushParameter().toByteArray())
                        .toShortHexString() + "\n");

                pushValueAccBuffer.write(Bytes.wrap(traceLine.getPushValueAcc().toByteArray())
                        .toShortHexString() + "\n");

                pushValueHighBuffer.write(Bytes.wrap(traceLine.getPushValueHigh().toByteArray())
                        .toShortHexString() + "\n");

           
                pushValueLowBuffer.write(Bytes.wrap(traceLine.getPushValueLow().toByteArray())
                        .toShortHexString() + "\n");
            
           
                validJumpDestinationBuffer.write((traceLine.getValidJumpDestination()
                        ? Bytes.of((byte) 0x01).toShortHexString()
                        : Bytes.of((byte) 0x00).toShortHexString()) + "\n");
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