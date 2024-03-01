package me.romo.linkbroker.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import me.romo.linkbroker.LinkBrokerApplication;
import me.romo.linkbroker.protocol.LinkPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PacketSerializer extends ByteToMessageCodec<LinkPacket> {

    private final Logger logger = LoggerFactory.getLogger("PacketSerializer");

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LinkPacket linkPacket, ByteBuf byteBuf) throws Exception {
        int index = byteBuf.writerIndex();
        byteBuf.writeZero(4);

        byteBuf.writeByte(linkPacket.getPacketId());
        linkPacket.encodePayload(byteBuf);

        int finalIndex = byteBuf.writerIndex();
        byteBuf.writerIndex(index);

        byteBuf.writeInt(finalIndex - index - 4);
        byteBuf.writerIndex(finalIndex);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(!byteBuf.isReadable(4)){
            return;
        }

        int index = byteBuf.readerIndex();

        int length = byteBuf.readInt();
        if(!byteBuf.isReadable(length)){
            byteBuf.readerIndex(index);
            return;
        }

        ByteBuf encoded = byteBuf.readSlice(length);
        encoded.markReaderIndex();

        byte packetId = encoded.readByte();
        LinkPacket linkPacket = LinkBrokerApplication.getPacketFactory().getPacket(packetId).getClone();
        linkPacket.decodePayload(encoded);

        list.add(linkPacket);
    }
}
