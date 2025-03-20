package io.github.u1tramarinet.android13app.util;

public class ByteConverter2 {
    public static int byteArrayToIntLittleEndian(byte[] byteArray) {
        if (byteArray.length != 3) {
            throw new IllegalArgumentException("Byte array must be 3 bytes long");
        }
        StringBuilder arrayStr = new StringBuilder();
        for (byte b : byteArray) {
            arrayStr.append(String.format("%s(%02X) ", b, b));
        }
        System.out.println("ByteConverter2:byteArray=" + arrayStr);
        return (byteArray[0] & 0xFF) | ((byteArray[1] & 0xFF) << 8) | ((byteArray[2] & 0xFF) << 16);
    }
}
