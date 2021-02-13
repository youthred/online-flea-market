package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class ChatMessageVO implements Serializable {

    private static final long serialVersionUID = 8353372227673157255L;

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
     * 消息内容
     */
    private String messageContent;
    /**
     * 消息类型
     */
    private Short messageTypeCode;
    /**
     * 消息发送者（是否买家发送）
     */
    private Boolean isFromBuyer;
}
