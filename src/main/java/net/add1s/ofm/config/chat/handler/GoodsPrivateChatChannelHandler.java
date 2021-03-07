package net.add1s.ofm.config.chat.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.pojo.entity.business.ChatMessage;
import org.springframework.stereotype.Component;

/**
 * 商品私聊NETTY逻辑处理
 *
 * @author pj.w@qq.com
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class GoodsPrivateChatChannelHandler extends SimpleChannelInboundHandler<ChatMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ChatMessage chatMessage) throws Exception {
        System.out.println(chatMessage);
    }
}
