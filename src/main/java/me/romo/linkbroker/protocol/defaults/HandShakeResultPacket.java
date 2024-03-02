package me.romo.linkbroker.protocol.defaults;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.protocol.LinkPacket;
import me.romo.linkbroker.protocol.handler.PacketHandler;

public class HandShakeResultPacket extends LinkPacket {

    private byte code;

    @Override
    public byte getPacketId() {
        return DefaultLinkPacketId.HANDSHAKE_RESULT_PACKET;
    }

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        byteBuf.writeByte(this.code);
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        //NOTHING: THIS PACKET IS NOT OUT BOUND.
        return;
    }

    @Override
    public boolean handle(PacketHandler packetHandler) {
        return packetHandler.handleHandShakeResultPacket(this);
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }
}
