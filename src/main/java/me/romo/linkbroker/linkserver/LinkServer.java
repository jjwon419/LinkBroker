package me.romo.linkbroker.linkserver;

import me.romo.linkbroker.protocol.LinkPacket;
import me.romo.linkbroker.protocol.defaults.HandShakePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class LinkServer {

    private final Logger logger = LoggerFactory.getLogger("LinkServer");

    private final LinkServerSession linkServerSession;

    public LinkServer(LinkServerSession linkServerSession){
        this.linkServerSession = linkServerSession;
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

    public void onHandShakePacket(HandShakePacket packet){

    }
}
