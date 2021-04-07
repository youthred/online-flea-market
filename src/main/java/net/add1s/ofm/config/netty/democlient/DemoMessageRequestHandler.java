package net.add1s.ofm.config.netty.democlient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoMessageRequestHandler extends SimpleChannelInboundHandler<DemoMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DemoMessage message) throws Exception {
        log.info("服务端收到: {}", message.toString());
        DemoMessage demoMessage = new DemoMessage().setA(123123);
        log.info("服务端返回: {}", demoMessage.toString());
        channelHandlerContext.channel().writeAndFlush(demoMessage);
    }
}
