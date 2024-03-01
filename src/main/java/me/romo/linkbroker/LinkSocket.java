package me.romo.linkbroker;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import me.romo.linkbroker.pipeline.LinkSocketServerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class LinkSocket extends Thread{

    private final Logger logger = LoggerFactory.getLogger("LinkSocket");

    private int port;

    private final EventLoopGroup bossLoopGroup;
    private final EventLoopGroup eventLoopGroup;

    private ChannelFuture linkServerFuture;

    public LinkSocket(int port){
        this.port = port;

        DefaultThreadFactory factory = new DefaultThreadFactory("LinkSocket", true);
        this.bossLoopGroup = new NioEventLoopGroup(0, factory);
        this.eventLoopGroup = new NioEventLoopGroup(0, factory);
        this.startServer();
        this.start();
    }

    @Override
    public void run() {
        while(true){
        }
    }

    private void startServer(){
        //BOOTSTRAP
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(this.bossLoopGroup, this.eventLoopGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new LinkSocketServerInitializer(logger));

        this.linkServerFuture = bootstrap.bind(new InetSocketAddress("0.0.0.0", port));
    }

    public void shutdown(){
        this.bossLoopGroup.shutdownGracefully();
        this.eventLoopGroup.shutdownGracefully();
        try{
            this.linkServerFuture.channel().closeFuture().sync();
        }catch (InterruptedException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }
}
