package net.add1s.ofm.pojo.vo.sys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.sys.SysRole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysRoleVO implements Serializable {

    private static final long serialVersionUID = 9211092502668145844L;

    public SysRoleVO(SysRole sysRole) {
        this.tbId = sysRole.getTbId();
        this.roleCode = sysRole.getRoleCode();
        this.roleName = sysRole.getRoleName();
        this.roleDesc = sysRole.getRoleDesc();
    }

    private Long tbId;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private boolean bound;

    private List<SysPermissionVO> sysPermissionVOS = new ArrayList<>();
}
