package net.add1s.ofm.pojo.entity.sys;

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
@TableName("t_sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1247444683051940790L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    /**
     * 用户名，唯一
     */
    @TableField("`username`")
    private String username;

    /**
     * 加密后的密码
     */
    @TableField("`password`")
    private String password;

    @TableField("`nickname`")
    private String nickname;

    @TableField("`email`")
    private String email;

    /**
     * 是否启用
     */
    @TableField("`enabled`")
    private Boolean enabled;

    @TableField("`create_time`")
    private LocalDateTime createTime;

    @TableField("`update_time`")
    private LocalDateTime updateTime;
}
