package me.romo.linkbroker.protocol.handler;

import me.romo.linkbroker.LinkBrokerApplication;
import me.romo.linkbroker.LinkSocket;
import me.romo.linkbroker.linkserver.LinkServer;
import me.romo.linkbroker.protocol.defaults.HandShakePacket;
import me.romo.linkbroker.protocol.defaults.HandShakeResultPacket;
import me.romo.linkbroker.protocol.defaults.type.HandShakeResultCode;

import java.util.Objects;

public class HandShakePacketHandler extends PacketHandler{

    private final LinkServer linkServer;

    public HandShakePacketHandler(LinkServer linkServer){
        this.linkServer = linkServer;
    }

    @Override
    public boolean handleHandshakePacket(HandShakePacket packet) {
        //CHECK PROTOCOL VERSION
        if(packet.getProtocolVersion() != LinkBrokerApplication.getProtocolVersion()){
            HandShakeResultPacket resultPacket = new HandShakeResultPacket();
            resultPacket.setCode(HandShakeResultCode.PROTOCOL_VERSION_DIFFERENT);
            this.linkServer.sendPacket(resultPacket);
            this.linkServer.disconnect();
            return true;
        }

        if(!Objects.equals(packet.getPassword(), LinkBrokerApplication.getPassword())){
            HandShakeResultPacket resultPacket = new HandShakeResultPacket();
            resultPacket.setCode(HandShakeResultCode.INVALID_PASSWORD);
            this.linkServer.sendPacket(resultPacket);
            this.linkServer.disconnect();
            return true;
        }

        HandShakeResultPacket resultPacket = new HandShakeResultPacket();
        resultPacket.setCode(HandShakeResultCode.SUCCESS);
        this.linkServer.sendPacket(resultPacket);
        this.linkServer.setPublicAddress(packet.getPublicAddress());
        this.linkServer.setName(packet.getName());
        return true;
    }
}
