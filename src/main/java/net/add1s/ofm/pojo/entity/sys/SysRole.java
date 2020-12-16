package net.add1s.ofm.pojo.entity.sys;

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
@TableName("t_sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 4439037815246514284L;

    @TableId(value = "tb_id", type = IdType.AUTO)
    private Long tbId;

    @TableField("role_code")
    private String roleCode;

    @TableField("role_name")
    private String roleName;

    @TableField("role_desc")
    private String roleDesc;
}
