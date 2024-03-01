package me.romo.linkbroker.protocol.defaults;

import io.netty.buffer.ByteBuf;
import me.romo.linkbroker.linkserver.LinkServer;
import me.romo.linkbroker.protocol.LinkPacket;
import me.romo.linkbroker.protocol.PacketHelper;

import java.net.InetSocketAddress;

public class HandShakePacket extends LinkPacket {

    private int protocolVersion;
    private String password;
    private InetSocketAddress publicAddress;

    @Override
    public byte getPacketId() {
        return 1;
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
    }

    @Override
    public void handle(LinkServer linkServer) {
        System.out.println(protocolVersion);
        System.out.println(password);
        System.out.println(publicAddress);
    }
}
