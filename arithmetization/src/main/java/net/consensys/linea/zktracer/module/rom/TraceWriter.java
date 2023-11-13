package net.consensys.linea.zktracer.module.rom;

import net.consensys.linea.zktracer.types.UnsignedByte;
import org.apache.tuweni.bytes.Bytes;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;

public class TraceWriter {

    private static final Map<String, FileWriter> stringFileWriterHashMap =
            new ConcurrentHashMap<>();
    public record FileWriter(FileOutputStream fileOutputStream, GZIPOutputStream gzipOutputStream, OutputStreamWriter outputStreamWriter, BufferedWriter bufferedWriter){};
    static Stream<Method> methodsStream = Arrays.stream(Trace.class.getDeclaredMethods());

    public static void writeTrace(final String moduleName, final String formattedDate, final Trace traceLine) {
        methodsStream
                .forEach(
                        method -> {
                            if (isGetter(method)) {
                                try {
                                    BufferedWriter fileWriter =
                                            stringFileWriterHashMap.computeIfAbsent(
                                                    method.getName().substring(3),
                                                    s -> {
                                                        String fileName = "/data/traces/%s/%s-%s".formatted(moduleName, formattedDate, s);
                                                        try {
                                                            FileOutputStream fos = new FileOutputStream(fileName);
                                                            GZIPOutputStream gos = new GZIPOutputStream(fos);
                                                            OutputStreamWriter osw = new OutputStreamWriter(gos, StandardCharsets.UTF_8);
                                                            return new FileWriter(fos,gos,osw, new BufferedWriter(osw));
                                                        } catch (IOException e) {
                                                            System.out.println("error trace " + e.getMessage());
                                                            throw new RuntimeException(e);
                                                        }
                                                    }).bufferedWriter();
                                    Object invoke = method.invoke(traceLine);
                                    if (invoke instanceof BigInteger) {
                                        fileWriter.write(
                                                Bytes.wrap(((BigInteger) method.invoke(traceLine)).toByteArray())
                                                        .toShortHexString());
                                        fileWriter.newLine();
                                    } else if (invoke instanceof Boolean) {
                                        fileWriter.write(
                                                ((Boolean) method.invoke(traceLine))
                                                        ? Bytes.of((byte) 0x01).toShortHexString()
                                                        : Bytes.of((byte) 0x00).toShortHexString());
                                        fileWriter.newLine();
                                    } else if (invoke instanceof ByteBuffer) {
                                        fileWriter.write(
                                                Bytes.wrap(((ByteBuffer) method.invoke(traceLine)).array())
                                                        .toShortHexString());
                                        fileWriter.newLine();
                                    } else if (invoke instanceof UnsignedByte) {
                                        fileWriter.write(
                                                Bytes.wrap(((UnsignedByte) method.invoke(traceLine)).toBigInteger().toByteArray())
                                                        .toShortHexString());
                                        fileWriter.newLine();
                                    } else {
                                        // ignore null value and add a new line
                                        fileWriter.newLine();
                                    }
                                } catch (Exception e) {
                                    System.out.println("error trace " + method);
                                }
                            }
                        });
    }

    public static void flush() {
        stringFileWriterHashMap.forEach(
                (s, writer) -> {
                    try {
                        writer.bufferedWriter.close();
                        writer.outputStreamWriter.close();
                        writer.gzipOutputStream.close();
                        writer.fileOutputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        stringFileWriterHashMap.clear();
    }

    private static boolean isGetter(Method method) {
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterCount() != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }
}
