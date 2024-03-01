package me.romo.linkbroker.protocol;

import me.romo.linkbroker.protocol.defaults.HandShakePacket;

import java.util.HashMap;
import java.util.Map;

public class PacketFactory {

    Map<Byte, LinkPacket> packets = new HashMap<>();

    public PacketFactory(){
        register(new HandShakePacket());
    }

    public void register(LinkPacket linkPacket){
        packets.put(linkPacket.getPacketId(), linkPacket);
    }

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
