package net.add1s.ofm.config.netty.democlient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoMessageResponseHandler extends SimpleChannelInboundHandler<DemoMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DemoMessage message) throws Exception {
        log.info("客户端收到: {}", message.toString());
    }
}
