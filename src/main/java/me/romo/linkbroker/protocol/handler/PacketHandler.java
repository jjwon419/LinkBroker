package me.romo.linkbroker.protocol.handler;

import me.romo.linkbroker.protocol.defaults.HandShakePacket;

public interface PacketHandler {

    public void handleHandshakePacket(HandShakePacket packet);
}
