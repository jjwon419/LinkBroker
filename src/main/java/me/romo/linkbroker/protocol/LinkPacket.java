package me.romo.linkbroker.protocol;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.linkserver.LinkServer;

public abstract class LinkPacket implements Cloneable{
    public abstract byte getPacketId();

    public abstract void encodePayload(ByteBuf byteBuf);

    public abstract void decodePayload(ByteBuf byteBuf);

    public abstract void handle(LinkServer linkServer);

    public LinkPacket getClone() throws CloneNotSupportedException {
        return (LinkPacket) this.clone();
    }
}
