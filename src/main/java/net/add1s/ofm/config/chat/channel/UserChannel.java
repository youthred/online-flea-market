package net.add1s.ofm.config.chat.channel;

import io.netty.channel.Channel;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.common.enums.Symbol;
import net.add1s.ofm.pojo.dto.ChatMessageDTO;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class UserChannel {

    private MyUserDetails userDetails;
    /**
     * 用户的私聊通道对应关系类，Key为“商品ID-买家ID-卖家ID”组合字符串
     */
    private List<GoodsChatChannel> goodsChatChannels = new ArrayList<>();

    public UserChannel addToGoodsChatChannels(Long goodsTbId, Long buyerSysUserTbId, Long sellerSysUserTbId, Channel channel) {
        this.goodsChatChannels.add(
                new GoodsChatChannel()
                        .setIdGoodsBuyerSeller(StringUtils.join(Arrays.asList(goodsTbId, buyerSysUserTbId, sellerSysUserTbId), Symbol.MINUS.getValue()))
                        .setChannel(channel)
        );
        return this;
    }

    public GoodsChatChannel getFromGoodsChatChannels(Long goodsTbId, Long buyerSysUserTbId, Long sellerSysUserTbId) {
        return this.goodsChatChannels
                .stream()
                .filter(goodsChatChannel -> goodsChatChannel.getIdGoodsBuyerSeller().equals(StringUtils.join(Arrays.asList(goodsTbId, buyerSysUserTbId, sellerSysUserTbId), Symbol.MINUS.getValue())))
                .findFirst()
                .orElse(null)
                ;
    }

    public GoodsChatChannel getFromGoodsChatChannels(ChatMessageDTO chatMessageDTO) {
        return this.goodsChatChannels
                .stream()
                .filter(goodsChatChannel -> goodsChatChannel.getIdGoodsBuyerSeller().equals(StringUtils.join(Arrays.asList(chatMessageDTO.getGoodsTbId(), chatMessageDTO.getBuyerSysUserTbId(), chatMessageDTO.getSellerSysUserTbId()), Symbol.MINUS.getValue())))
                .findFirst()
                .orElse(null)
                ;
    }
}
