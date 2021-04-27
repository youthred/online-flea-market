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
@TableName("t_sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = -5342581624490942720L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    @TableField("`permission_url`")
    private String permissionUrl;

    @TableField("`request_method`")
    private String requestMethod;

    @TableField("`permission_code`")
    private String permissionCode;

    @TableField("`permission_name`")
    private String permissionName;

    @TableField("`permission_desc`")
    private String permissionDesc;

    /**
     * 同tbId
     */
    @TableField("`id`")
    private Long id;

    @TableField("`pid`")
    private Long pid;

    /**
     * 是否无需验证
     */
    @TableField("`permit_any`")
    private Boolean permitAny;
}
