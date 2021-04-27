package net.add1s.ofm.pojo.vo.sys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysBindRolePermissionVO implements Serializable {

    private static final long serialVersionUID = -2872297694531669577L;

    public SysBindRolePermissionVO(Long sysRoleTbId, Long sysPermissionTbId) {
        this.roleTbId = sysRoleTbId;
        this.permissionTbId = sysPermissionTbId;
    }

    private Long roleTbId;
    private Long permissionTbId;
}
