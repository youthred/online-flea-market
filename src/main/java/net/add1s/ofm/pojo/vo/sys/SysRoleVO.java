package net.add1s.ofm.pojo.vo.sys;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class SysRoleVO implements Serializable {

    private static final long serialVersionUID = 9211092502668145844L;

    private Long tbId;
    private String roleCode;
    private String roleName;
    private String roleDesc;

    private List<SysPermissionVO> sysPermissionVOS = new ArrayList<>();
}
