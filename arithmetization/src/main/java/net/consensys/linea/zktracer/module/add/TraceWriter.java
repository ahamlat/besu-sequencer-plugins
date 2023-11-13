package net.consensys.linea.zktracer.module.add;

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

import org.apache.tuweni.bytes.Bytes;

public class TraceWriter {

    public static final Method[] DECLARED_METHODS = Trace.class.getDeclaredMethods();
    private static final Map<String, BufferedWriter> stringFileWriterHashMap =
            new ConcurrentHashMap<>();

    public static void writeTrace(final String moduleName, final String formattedDate,final Trace traceLine) {
        Stream<Method> methodsStream = Arrays.stream(DECLARED_METHODS);
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
                                                            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                                                            return new BufferedWriter(osw);
                                                        } catch (IOException e) {
                                                            System.out.println("error trace " + e.getMessage());
                                                            throw new RuntimeException(e);
                                                        }
                                                    });
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
                                    } else {
                                        // ignore null value and add a new line
                                        fileWriter.newLine();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error invoking method: " + method);
                                }
                            }
                        });
    }

    public static void flush() {
        stringFileWriterHashMap.forEach(
                (s, writer) -> {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        stringFileWriterHashMap.clear();
    }

    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterCount() != 0) return false;
        return !void.class.equals(method.getReturnType());
    }
}
