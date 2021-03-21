package net.add1s.ofm.config.chat.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.config.chat.channel.UserChannel;
import net.add1s.ofm.config.chat.pojo.NettyChatMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商品私聊NETTY逻辑处理
 *
 * @author pj.w@qq.com
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class GoodsPrivateChatChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final List<UserChannel> USER_CHANNELS = new ArrayList<>();
    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_GROUP.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_GROUP.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(Arrays.toString(ExceptionUtils.getStackFrames(cause)));
        ctx.channel().close();
        CHANNEL_GROUP.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.info(textWebSocketFrame.text());
        NettyChatMessage nettyChatMessage = JSON.parseObject(textWebSocketFrame.text(), NettyChatMessage.class);
        log.info(nettyChatMessage.toString());
    }
}
