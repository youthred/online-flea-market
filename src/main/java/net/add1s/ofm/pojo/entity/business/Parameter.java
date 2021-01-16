package net.add1s.ofm.pojo.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 参数数据字典
 *
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@TableName("t_parameter")
public class Parameter implements Serializable {

    private static final long serialVersionUID = -4010591935801839548L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    @TableField("`group_name`")
    private String groupName;

    @TableField("`code`")
    private String code;

    @TableField("`desc`")
    private String desc;
}
