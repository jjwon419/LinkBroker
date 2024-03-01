package me.romo.linkbroker.protocol;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.linkserver.LinkServer;

public class LinkServerPacket extends LinkPacket{

    private final byte packetId;

    private ByteBuf byteBuf;

    public LinkServerPacket(byte packetId){
        this.packetId = packetId;
    }

    @Override
    public byte getPacketId() {
        return this.packetId;
    }

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        byteBuf.writeBytes(this.byteBuf);
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        this.byteBuf = byteBuf.readRetainedSlice(byteBuf.readableBytes());
    }

    @Override
    public void handle(LinkServer linkServer) {
        return;
    }
}
