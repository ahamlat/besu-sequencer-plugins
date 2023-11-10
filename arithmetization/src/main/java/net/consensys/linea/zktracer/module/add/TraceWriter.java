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

import net.consensys.linea.zktracer.types.UnsignedByte;
import org.apache.tuweni.bytes.Bytes;

public class TraceWriter {

    private static final Map<String, FileWriter> stringFileWriterHashMap =
            new ConcurrentHashMap<>();

    record FileWriter(FileOutputStream fileOutputStream, GZIPOutputStream gzipOutputStream, OutputStreamWriter outputStreamWriter, BufferedWriter bufferedWriter){};

    public static void writeTrace(final String moduleName, final Trace traceLine) {
    Stream<Method> methodsStream = Arrays.stream(Trace.class.getDeclaredMethods());

    methodsStream
        .parallel()
        .forEach(
            method -> {
              if (isMethodToTrace(method)) {
                try {

                    BufferedWriter fileWriter =
                            stringFileWriterHashMap.computeIfAbsent(
                                    method.getName(),
                                    s -> {
                                        String fileName = "/data/traces/%s/%s".formatted(moduleName, s);
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
                        writer.fileOutputStream.close();
                        writer.gzipOutputStream.close();
                        writer.outputStreamWriter.close();
                        writer.bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

  private static boolean isMethodToTrace(Method method) {
    if (method.getReturnType().equals(BigInteger.class) || method.getReturnType().equals(UnsignedByte.class)) return true;
    else if (method.getName().contains("overflow")) return true;
    else return false;
  }
}
