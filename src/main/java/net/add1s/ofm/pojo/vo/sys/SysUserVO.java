package net.add1s.ofm.pojo.vo.sys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.sys.SysUser;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = 7550308910589311484L;

    public SysUserVO(SysUser sysUser) {
        this.tbId = sysUser.getTbId();
        this.username = sysUser.getUsername();
        this.password = sysUser.getPassword();
        this.nickname = sysUser.getNickname();
        this.email = sysUser.getEmail();
        this.enabled = sysUser.getEnabled();
        this.createTime = sysUser.getCreateTime();
        this.updateTime = sysUser.getUpdateTime();
    }

    private Long tbId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Boolean enabled;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private List<SysRoleVO> sysRoleVOS = new ArrayList<>();
}
