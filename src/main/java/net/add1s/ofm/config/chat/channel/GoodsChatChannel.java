package net.add1s.ofm.config.chat.channel;

import lombok.Data;
import lombok.experimental.Accessors;
import io.netty.channel.Channel;

/**
 * 定位一个channel和“商品-买家-卖家”(一个私聊)的关系
 * 
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class GoodsChatChannel {
    
    private String idGoodsBuyerSeller;
    private Channel channel;
}
