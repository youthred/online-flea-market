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
@TableName("t_goods_order")
public class GoodsOrder implements Serializable {

    private static final long serialVersionUID = -3620984270876393464L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    /**
     * 订单创建时间
     */
    @TableField("`create_time`")
    private LocalDateTime createTime;

    /**
     * 订单完成时间
     */
    @TableField("`complete_time`")
    private LocalDateTime completeTime;

    /**
     * 商品TBID
     */
    @TableField("`goods_tb_id`")
    private Long goodsTbId;

    /**
     * 是否订下
     */
    @TableField("`ordered`")
    private Boolean ordered;

    /**
     * 是否付款
     */
    @TableField("`paid`")
    private Boolean paid;

    /**
     * 买家TBID
     */
    @TableField("`buyer_sys_user_tb_id`")
    private Long buyerSysUserTbId;
}
