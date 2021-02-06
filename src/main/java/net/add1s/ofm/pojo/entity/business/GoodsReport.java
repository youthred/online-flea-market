package net.add1s.ofm.pojo.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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

    @TableField("goods_tb_id")
    private Long goodsTbId;

    @TableField("reason")
    private String reason;

    @TableField("reviewed")
    private Boolean reviewed;

    /**
     * 审查人TBID
     */
    @TableField("`sys_user_tb_id`")
    private Long sysUserTbId;
}
