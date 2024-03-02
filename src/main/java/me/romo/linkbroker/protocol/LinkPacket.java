package me.romo.linkbroker.protocol;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.linkserver.LinkServer;
import me.romo.linkbroker.protocol.handler.PacketHandler;

public abstract class LinkPacket implements Cloneable{
    public abstract byte getPacketId();

    public abstract void encodePayload(ByteBuf byteBuf);

    public abstract void decodePayload(ByteBuf byteBuf);

    public abstract boolean handle(PacketHandler packetHandler);

    public LinkPacket getClone() throws CloneNotSupportedException {
        return (LinkPacket) this.clone();
    }
}
