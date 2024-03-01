package me.romo.linkbroker.pipeline;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.romo.linkbroker.LinkBrokerApplication;
import me.romo.linkbroker.linkserver.LinkServer;
import me.romo.linkbroker.linkserver.LinkServerSession;
import org.slf4j.Logger;

public class LinkSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    private final Logger logger;

    public LinkSocketServerInitializer(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        logger.info(socketChannel.remoteAddress() + " socket opened...");

        LinkServer linkServer = new LinkServer(new LinkServerSession(socketChannel));

        LinkBrokerApplication.getLinkServerFactory().addLinkServer(linkServer);

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("PacketSerializer", new PacketSerializer());
        pipeline.addLast("ReadPacketHandler", new ReadPacketHandler(linkServer));
    }
}
