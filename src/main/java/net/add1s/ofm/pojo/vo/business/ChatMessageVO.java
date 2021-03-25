package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.business.ChatMessage;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ChatMessageVO implements Serializable {

    private static final long serialVersionUID = 8353372227673157255L;

    public ChatMessageVO(ChatMessage chatMessage) {
        this.tbId = chatMessage.getTbId();
        this.createTime = chatMessage.getCreateTime();
        this.goodsTbId = chatMessage.getGoodsTbId();
        this.buyerSysUserTbId = chatMessage.getBuyerSysUserTbId();
        this.sellerSysUserTbId = chatMessage.getSellerSysUserTbId();
        this.messageContent = chatMessage.getMessageContent();
        this.messageTypeCode = chatMessage.getMessageTypeCode();
        this.senderSysUserTbId = chatMessage.getSenderSysUserTbId();
        this.readBuyer = chatMessage.isReadBuyer();
        this.readSeller = chatMessage.isReadSeller();
    }

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

    public ChatMessageVO setIsFromCurrentUser(Long currentUserTbId) {
        this.isFromCurrentUser = this.senderSysUserTbId.equals(currentUserTbId);
        return this;
    }
}
