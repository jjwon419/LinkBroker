package me.romo.linkbroker.protocol;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class PacketHelper {

    public static byte[] read(ByteBuf buf, int len) {
        if (len < 0) {
            return new byte[0];
        } else {
            byte[] bytes = new byte[len];
            buf.readBytes(bytes);
            return bytes;
        }
    }

    public static void write(ByteBuf buf, byte[] bytes) {
        if (bytes != null) {
            buf.writeBytes(bytes);
        }
    }

    public static byte[] readByteArray(ByteBuf buf) {
        return read(buf, buf.readInt());
    }

    public static void writeByteArray(ByteBuf buf, byte[] bytes) {
        buf.writeInt(bytes.length);
        write(buf, bytes);
    }

    public static boolean readBoolean(ByteBuf buf) {
        return buf.readByte() == 1;
    }

    public static void writeBoolean(ByteBuf buf, boolean bool) {
        buf.writeByte(bool ? 1 : 0);
    }

    public static void writeString(ByteBuf buf, String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        writeByteArray(buf, bytes);
    }

    public static String readString(ByteBuf buf) {
        return new String(readByteArray(buf), StandardCharsets.UTF_8);
    }
}
