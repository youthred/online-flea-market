package net.add1s.ofm.config.netty.democlient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class DemoMessageEncoder extends MessageToByteEncoder<DemoMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, DemoMessage demoMessage, ByteBuf byteBuf) throws Exception {
        demoMessage.write(byteBuf);
    }
}
