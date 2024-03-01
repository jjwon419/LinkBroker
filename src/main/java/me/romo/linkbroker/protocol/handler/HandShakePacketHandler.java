package me.romo.linkbroker.protocol.handler;

import me.romo.linkbroker.linkserver.LinkServer;
import me.romo.linkbroker.protocol.defaults.HandShakePacket;

public class HandShakePacketHandler implements PacketHandler{

    private LinkServer linkServer;

    public HandShakePacketHandler(LinkServer linkServer){
        this.linkServer = linkServer;
    }

    @Override
    public void handleHandshakePacket(HandShakePacket packet) {
        this.linkServer.onHandShakePacket(packet);

        //TODO: LINK SERVER FACTORY
    }
}
