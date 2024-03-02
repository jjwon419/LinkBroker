package me.romo.linkbroker.protocol.defaults;

public interface DefaultLinkPacketId {
    byte HANDSHAKE_PACKET = 0x01;
    byte HANDSHAKE_RESULT_PACKET = 0x02;
    byte ADD_LINK_SERVER_PACKET = 0x03;
    byte REMOVE_LINK_SERVER_PACKET = 0x04;
}
