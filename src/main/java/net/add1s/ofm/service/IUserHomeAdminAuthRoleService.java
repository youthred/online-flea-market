package net.add1s.ofm.service;

import cn.hutool.core.lang.tree.Tree;
import net.add1s.ofm.pojo.entity.sys.SysRole;

import java.util.List;

public interface IUserHomeAdminAuthRoleService {

    List<SysRole> list();

    List<Tree<String>> permissionBoundTree(Long sysRoleTbId);

    void permissionBind(Long sysRoleTbId, List<Long> sysPermissionBoundTbIds);
}
