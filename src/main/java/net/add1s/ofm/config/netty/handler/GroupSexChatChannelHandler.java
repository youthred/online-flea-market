package net.add1s.ofm.config.netty.handler;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ChannelHandler.Sharable
public class GroupSexChatChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        log.info("与客户端建立连接，通道开启：{}", ctx.channel());
        CHANNEL_GROUP.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        log.info("与客户端断开连接，通道关闭：{}", ctx.channel());
        CHANNEL_GROUP.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        CHANNEL_GROUP.writeAndFlush(new TextWebSocketFrame(
                StrUtil.format(
                        "{}: {}",
                        channelHandlerContext.channel().remoteAddress().toString(),
                        textWebSocketFrame.text().trim()
                )
        ));
    }
}
