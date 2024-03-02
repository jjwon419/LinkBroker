package me.romo.linkbroker.protocol.defaults;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.protocol.LinkPacket;
import me.romo.linkbroker.protocol.PacketHelper;
import me.romo.linkbroker.protocol.handler.PacketHandler;

import java.net.InetSocketAddress;

public class AddLinkServerPacket extends LinkPacket {

    private InetSocketAddress remoteAddress;
    private String name;

    @Override
    public byte getPacketId() {
        return DefaultLinkPacketId.ADD_LINK_SERVER_PACKET;
    }

    @Override
    public void encodePayload(ByteBuf byteBuf) {

    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {

    }

    @Override
    public boolean handle(PacketHandler packetHandler) {
        return false;
    }


}
