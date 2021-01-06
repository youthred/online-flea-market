package net.add1s.ofm.pojo.vo.sys;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = 7550308910589311484L;

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
