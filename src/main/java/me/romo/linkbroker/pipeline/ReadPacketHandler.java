package me.romo.linkbroker.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.romo.linkbroker.linkserver.LinkServer;
import me.romo.linkbroker.protocol.LinkPacket;
import me.romo.linkbroker.protocol.LinkServerPacket;

public class ReadPacketHandler extends SimpleChannelInboundHandler<LinkPacket> {

    private LinkServer linkServer;

    public ReadPacketHandler(LinkServer linkServer){
        this.linkServer = linkServer;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //TODO: disconnect
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LinkPacket packet) throws Exception {
        packet.handle(linkServer.getPacketHandler());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        linkServer.getLogger().error(cause.getMessage());
        cause.printStackTrace();
    }
}
