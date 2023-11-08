package net.consensys.linea.zktracer.module.add;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.apache.tuweni.bytes.Bytes;

public class TraceWriter {

  private static final Map<String, BufferedWriter> stringFileWriterHashMap =
      new ConcurrentHashMap<>();

  public static void writeTrace(final String moduleName, final Trace traceLine) {
    Stream<Method> methodsStream = Arrays.stream(Trace.class.getDeclaredMethods());
    methodsStream
        .parallel()
        .forEach(
            method -> {
              if (isGetter(method)) {
                try {
                  BufferedWriter fileWriter =
                      stringFileWriterHashMap.computeIfAbsent(
                          method.getName().substring(3),
                          s -> {
                            try {
                              return new BufferedWriter(
                                  new FileWriter("/data/traces/%s/%s".formatted(moduleName, s)));
                            } catch (IOException e) {
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
        (s, fileOutputStream) -> {
          try {
            fileOutputStream.close();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
  }

  private static boolean isGetter(Method method) {
    if (!method.getName().startsWith("get")) return false;
    if (method.getParameterCount() != 0) return false;
    return !void.class.equals(method.getReturnType());
  }
}