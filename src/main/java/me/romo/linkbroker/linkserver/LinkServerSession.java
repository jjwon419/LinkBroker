package me.romo.linkbroker.linkserver;

import io.netty.channel.socket.SocketChannel;
import io.netty.util.internal.PlatformDependent;
import me.romo.linkbroker.protocol.LinkPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class LinkServerSession {
    private final Logger logger;

    private final InetSocketAddress remoteAddress;

    private final SocketChannel channel;

    private final Queue<LinkPacket> packetQueue = PlatformDependent.newMpscQueue();

    public LinkServerSession(SocketChannel channel){
        this.remoteAddress = channel.remoteAddress();
        this.channel = channel;
        logger = LoggerFactory.getLogger(remoteAddress.toString());

        channel.eventLoop().scheduleAtFixedRate(this::run, 50, 50, TimeUnit.MILLISECONDS);
    }

    public void run(){
        LinkPacket packet;
        while((packet = this.packetQueue.poll()) != null){
            this.channel.writeAndFlush(packet);
        }
    }

    public void sendPacket(LinkPacket packet){
        this.packetQueue.add(packet);
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }
}
