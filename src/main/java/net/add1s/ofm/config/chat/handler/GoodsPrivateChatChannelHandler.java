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
import net.add1s.ofm.config.chat.channel.GoodsChatChannel;
import net.add1s.ofm.config.chat.channel.UserChannel;
import net.add1s.ofm.config.chat.pojo.NettyChatMessage;
import net.add1s.ofm.pojo.dto.ChatMessageDTO;
import net.add1s.ofm.service.IChatMessageService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    private final IChatMessageService iChatMessageService;

    public GoodsPrivateChatChannelHandler(IChatMessageService iChatMessageService) {
        this.iChatMessageService = iChatMessageService;
    }

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
        USER_CHANNELS.forEach(userChannel -> userChannel.getGoodsChatChannels().removeIf(goodsChatChannel -> goodsChatChannel.getChannel().id().asShortText().equals(ctx.channel().id().asShortText())));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 接收到的消息
        NettyChatMessage nettyChatMessage = JSON.parseObject(textWebSocketFrame.text(), NettyChatMessage.class);
        nettyChatMessage.getChatMessageDTO().setCreateTime(LocalDateTime.now());
        if (nettyChatMessage.getChatMessageDTO().isUserGoodsChannelConnectBind()) {
            // 添加到用户-商品-通道绑定集合里
            addToUserChannelList(ctx, nettyChatMessage);
        } else {
            // 聊天消息入库
            iChatMessageService.save(nettyChatMessage.getChatMessageDTO().toEntity());
            // 若对方用户的对应通道存在，便通过WebSocket发送给对方
            sendToOtherSide(nettyChatMessage.getChatMessageDTO().setOtherSideTbId());
        }
    }

    /**
     * 若对方用户的对应通道存在就通过WebSocket发送消息给对方
     *
     * @param chatMessageDTO ChatMessageDTO
     */
    private void sendToOtherSide(ChatMessageDTO chatMessageDTO) {
        GoodsChatChannel otherSideGoodsChatChannel = getGoodsChatChannelFromUserChannelOfOtherSide(chatMessageDTO);
        if (Objects.nonNull(otherSideGoodsChatChannel)) {
            otherSideGoodsChatChannel.getChannel().writeAndFlush(new TextWebSocketFrame(
                    new ChatMessageDTO()
                            .setMessageContent(chatMessageDTO.getMessageContent())
                            .setMessageTypeCode((short) 1)
                            .setCreateTime(chatMessageDTO.getCreateTime())
                            .toJsonString()
            ));
        }
    }

    /**
     * 不存在则添加，已存在但通道已改变则修改，已存在未改变不动
     *
     * @param ctx              ChannelHandlerContext
     * @param nettyChatMessage NettyChatMessage
     */
    private void addToUserChannelList(ChannelHandlerContext ctx, NettyChatMessage nettyChatMessage) {
        GoodsChatChannel goodsChatChannelFromUserChannelOfSender = getGoodsChatChannelFromUserChannelOfSender(nettyChatMessage.getChatMessageDTO());
        UserChannel userChannel = getFromUserChannel(nettyChatMessage.getCurrentUserDetails().getTbId());
        if (Objects.nonNull(userChannel)) {
            // 用户存在但对应当前[商品-通道]不存在->新添加[商品-通道]
            if (Objects.isNull(goodsChatChannelFromUserChannelOfSender)) {
                userChannel.addToGoodsChatChannels(
                        nettyChatMessage.getChatMessageDTO().getGoodsTbId(),
                        nettyChatMessage.getChatMessageDTO().getBuyerSysUserTbId(),
                        nettyChatMessage.getChatMessageDTO().getSellerSysUserTbId(),
                        ctx.channel()
                );
            }
            // 已存在但通道已改变则修改
            else if (!goodsChatChannelFromUserChannelOfSender.getChannel().id().asShortText().equals(ctx.channel().id().asShortText())) {
                goodsChatChannelFromUserChannelOfSender.setChannel(ctx.channel());
            }
        } else {
            // 当前[用户-商品-通道]不存在->新添加[用户-商品-通道]
            USER_CHANNELS.add(
                    new UserChannel()
                            .setUserDetails(nettyChatMessage.getCurrentUserDetails())
                            .addToGoodsChatChannels(
                                    nettyChatMessage.getChatMessageDTO().getGoodsTbId(),
                                    nettyChatMessage.getChatMessageDTO().getBuyerSysUserTbId(),
                                    nettyChatMessage.getChatMessageDTO().getSellerSysUserTbId(),
                                    ctx.channel()
                            )
            );
        }
    }

    /**
     * 用户ID和商品通道组合都不存在时添加。也就是说一个用户可以维护多个通道连接。
     *
     * @param nettyChatMessage NettyChatMessage
     * @return 是否已存在相同的用户和商品通道组合
     */
    private boolean isUserChannelExist(NettyChatMessage nettyChatMessage) {
        return USER_CHANNELS.stream().anyMatch(userChannel ->
                userChannel.getUserDetails().getTbId().equals(nettyChatMessage.getCurrentUserDetails().getTbId())
                        && Objects.nonNull(userChannel.getFromGoodsChatChannels(nettyChatMessage.getChatMessageDTO()))
        );
    }

    /**
     * 通过用户ID获取该用户所有已连接通道
     *
     * @param userDetailTbId 用户ID
     * @return UserChannel
     */
    private UserChannel getFromUserChannel(Long userDetailTbId) {
        return USER_CHANNELS.stream().filter(userChannel -> userChannel.getUserDetails().getTbId().equals(userDetailTbId)).findFirst().orElse(null);
    }

    /**
     * 获取当前连接的发送者的通道
     *
     * @param chatMessageDTO ChatMessageDTO
     * @return GoodsChatChannel
     */
    private GoodsChatChannel getGoodsChatChannelFromUserChannelOfSender(ChatMessageDTO chatMessageDTO) {
        UserChannel currentUserChannel = getFromUserChannel(chatMessageDTO.getSenderSysUserTbId());
        return Objects.nonNull(currentUserChannel) ? currentUserChannel.getFromGoodsChatChannels(chatMessageDTO) : null;
    }

    /**
     * 获取当前连接的对方的通道
     *
     * @param chatMessageDTO ChatMessageDTO
     * @return GoodsChatChannel
     */
    private GoodsChatChannel getGoodsChatChannelFromUserChannelOfOtherSide(ChatMessageDTO chatMessageDTO) {
        UserChannel currentUserChannel = getFromUserChannel(chatMessageDTO.getOtherSideTbId());
        return Objects.nonNull(currentUserChannel) ? currentUserChannel.getFromGoodsChatChannels(chatMessageDTO) : null;
    }
}
