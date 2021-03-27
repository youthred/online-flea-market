package net.add1s.ofm.pojo.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.business.ChatMessage;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class ChatMessageDTO implements Serializable {

    private static final long serialVersionUID = -3322096355972862615L;

    private Long tbId;
    /**
     * 消息创建时间
     */
    private LocalDateTime createTime;
    /**
     * 商品TBID
     */
    private Long goodsTbId;
    /**
     * 买家用户TBID
     */
    private Long buyerSysUserTbId;
    /**
     * 卖家用户TBID
     */
    private Long sellerSysUserTbId;
    /**
     * 消息内容
     */
    private String messageContent;
    /**
     * 消息类型
     */
    private Short messageTypeCode;
    /**
     * 消息发送者用户TBID
     */
    private Long senderSysUserTbId;
    /**
     * 买家已读
     */
    private boolean readBuyer;
    /**
     * 卖家已读
     */
    private boolean readSeller;
    /**
     * 是否当前用户所发送的消息
     */
    private boolean isFromCurrentUser;
    /**
     * 对方用户TBID
     */
    private Long otherSideTbId;
    /**
     * 是否属于用户-商品-通道绑定消息，如果是则不进行消息的转发，仅绑定维护对应关系
     */
    private boolean userGoodsChannelConnectBind;

    public ChatMessageDTO setIsFromCurrentUser(Long currentUserTbId) {
        this.isFromCurrentUser = this.senderSysUserTbId.equals(currentUserTbId);
        return this;
    }

    public ChatMessageDTO setOtherSideTbId() {
        this.otherSideTbId = this.senderSysUserTbId.equals(this.buyerSysUserTbId) ? this.sellerSysUserTbId : this.buyerSysUserTbId;
        return this;
    }

    public ChatMessage toEntity() {
        return new ChatMessage()
            .setTbId(this.tbId)
            .setCreateTime(this.createTime)
            .setGoodsTbId(this.goodsTbId)
            .setBuyerSysUserTbId(this.buyerSysUserTbId)
            .setSellerSysUserTbId(this.sellerSysUserTbId)
            .setMessageContent(this.messageContent)
            .setMessageTypeCode(this.messageTypeCode)
            .setSenderSysUserTbId(this.senderSysUserTbId)
            .setReadBuyer(this.readBuyer)
            .setReadSeller(this.readSeller);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}
