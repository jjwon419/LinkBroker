package me.romo.linkbroker.protocol.handler;

import me.romo.linkbroker.protocol.defaults.HandShakePacket;
import me.romo.linkbroker.protocol.defaults.HandShakeResultPacket;

public class PacketHandler {

    public boolean handleHandshakePacket(HandShakePacket packet){
        return false;
    };

    public boolean handleHandShakeResultPacket(HandShakeResultPacket packet){
        return false;
    }
}
