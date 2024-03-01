package me.romo.linkbroker.protocol;

import java.util.HashMap;
import java.util.Map;

public class PacketFactory {

    Map<Byte, LinkPacket> packets = new HashMap<>();

    public Map<Byte, LinkPacket> getAllPackets() {
        return packets;
    }

    public LinkPacket getPacket(byte id){
        LinkPacket packet = packets.get(id);
        if(packet == null){
            packet = new LinkServerPacket(id);
        }
        return packet;
    }
}
