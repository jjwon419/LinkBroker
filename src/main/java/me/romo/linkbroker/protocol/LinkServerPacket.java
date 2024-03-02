package me.romo.linkbroker.protocol;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.linkserver.LinkServer;
import me.romo.linkbroker.protocol.handler.PacketHandler;

public class LinkServerPacket extends LinkPacket{

    private final byte packetId;

    private LinkServer targetServer;

    private ByteBuf byteBuf;

    public LinkServerPacket(byte packetId){
        this.packetId = packetId;
    }

    public LinkServerPacket(byte packetId, LinkServer targetServer){
        this.packetId = packetId;
        this.targetServer = targetServer;
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
    public boolean handle(PacketHandler packetHandler) {
        return true;
    }

    public LinkServer getTargetServer() {
        return targetServer;
    }

    public void setTargetServer(LinkServer targetServer) {
        this.targetServer = targetServer;
    }
}
