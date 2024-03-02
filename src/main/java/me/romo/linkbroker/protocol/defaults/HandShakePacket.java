package me.romo.linkbroker.protocol.defaults;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.protocol.LinkPacket;
import me.romo.linkbroker.protocol.PacketHelper;
import me.romo.linkbroker.protocol.handler.PacketHandler;

import java.net.InetSocketAddress;

public class HandShakePacket extends LinkPacket {

    private int protocolVersion;
    private String password;
    private InetSocketAddress publicAddress;
    private String name;

    @Override
    public byte getPacketId() {
        return DefaultLinkPacketId.HANDSHAKE_PACKET;
    }

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        //NOTHING: THIS PACKET IS NOT IN BOUND.
        return;
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        this.protocolVersion = byteBuf.readInt();
        this.password = PacketHelper.readString(byteBuf);
        this.publicAddress = new InetSocketAddress(PacketHelper.readString(byteBuf), byteBuf.readInt());
        this.name = PacketHelper.readString(byteBuf);
    }

    @Override
    public boolean handle(PacketHandler packetHandler) {
        return packetHandler.handleHandshakePacket(this);
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getPassword() {
        return password;
    }

    public InetSocketAddress getPublicAddress() {
        return publicAddress;
    }

    public String getName() {
        return name;
    }
}
