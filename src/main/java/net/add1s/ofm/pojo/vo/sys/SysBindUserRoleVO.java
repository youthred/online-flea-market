package net.add1s.ofm.pojo.vo.sys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysBindUserRoleVO implements Serializable {

    private static final long serialVersionUID = -5570329226342426376L;

    public SysBindUserRoleVO(Long sysUserTbId, Long sysRoleTbId) {
        this.userTbId = sysUserTbId;
        this.roleTbId = sysRoleTbId;
    }

    private Long userTbId;
    private Long roleTbId;
}
