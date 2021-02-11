package net.add1s.ofm.pojo.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@TableName("t_chat_message")
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = -3338795305315843400L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    /**
     * 消息创建时间
     */
    @TableField("`create_time`")
    private LocalDateTime createTime;

    /**
     * 商品TBID
     */
    @TableField("`goods_tb_id`")
    private Long goodsTbId;

    /**
     * 买家用户TBID
     */
    @TableField("`buyer_sys_user_tb_id`")
    private Long buyerSysUserTbId;

    /**
     * 消息内容
     */
    @TableField("`message_content`")
    private String messageContent;

    /**
     * 消息类型
     */
    @TableField("`message_type_code`")
    private Short messageTypeCode;

    /**
     * 消息发送者（是否买家发送）
     */
    @TableField("`is_from_buyer`")
    private Boolean isFromBuyer;
}
