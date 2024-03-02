package me.romo.linkbroker.linkserver;

import me.romo.linkbroker.LinkBrokerApplication;
import me.romo.linkbroker.protocol.LinkPacket;
import me.romo.linkbroker.protocol.handler.HandShakePacketHandler;
import me.romo.linkbroker.protocol.handler.PacketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class LinkServer {

    private final Logger logger = LoggerFactory.getLogger("LinkServer");

    private final LinkServerSession linkServerSession;

    private PacketHandler packetHandler = null;

    private String name;
    private InetSocketAddress publicAddress;

    public LinkServer(LinkServerSession linkServerSession){
        this.linkServerSession = linkServerSession;
        this.setPacketHandler(new HandShakePacketHandler(this));
    }

    public InetSocketAddress getRemoteAddress(){
        return this.linkServerSession.getRemoteAddress();
    }

    public void sendPacket(LinkPacket packet){
        this.linkServerSession.sendPacket(packet);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setPacketHandler(PacketHandler packetHandler){
        this.packetHandler = packetHandler;
    }

    public PacketHandler getPacketHandler(){
        return packetHandler;
    }

    public InetSocketAddress getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(InetSocketAddress publicAddress) {
        if(this.publicAddress != null){
            LinkBrokerApplication.getLinkServerFactory().unMatchLinkServerPublicAddress(this);
        }
        this.publicAddress = publicAddress;
        LinkBrokerApplication.getLinkServerFactory().matchLinkServerPublicAddress(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(this.name != null){
            LinkBrokerApplication.getLinkServerFactory().unMatchLinkServerName(this);
        }
        this.name = name;
        LinkBrokerApplication.getLinkServerFactory().matchLinkServerName(this);
    }

    public void disconnect(){
        //TODO: send disconnect packet
        this.onDisconnect();
    }

    public void onDisconnect(){
        LinkBrokerApplication.getLinkServerFactory().onDisconnectLinkServer(this);
        this.linkServerSession.disconnect();
    }
}
