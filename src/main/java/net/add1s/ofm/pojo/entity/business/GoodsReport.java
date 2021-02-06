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
@TableName("t_goods_report")
public class GoodsReport implements Serializable {

    private static final long serialVersionUID = 2647617979648280091L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    @TableField("`create_time`")
    private LocalDateTime createTime;

    @TableField("`goods_tb_id`")
    private Long goodsTbId;

    @TableField("`reason`")
    private String reason;

    /**
     * 是否已审查
     */
    @TableField("`reviewed`")
    private Boolean reviewed;

    /**
     * 是否举报通过（下架对应商品）
     */
    @TableField("`passed`")
    private Boolean passed;

    /**
     * 审查人TBID
     */
    @TableField("`sys_user_tb_id`")
    private Long sysUserTbId;

    @TableField("`review_time`")
    private LocalDateTime reviewTime;
}
