package net.add1s.ofm.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import net.add1s.ofm.common.enums.SysRoleEnum;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.pojo.entity.sys.SysRole;
import net.add1s.ofm.pojo.vo.sys.SysPermissionVO;
import net.add1s.ofm.service.ISysPermissionService;
import net.add1s.ofm.service.ISysRoleService;
import net.add1s.ofm.service.IUserHomeAdminAuthRoleService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UserHomeAdminAuthRoleServiceImpl implements IUserHomeAdminAuthRoleService {

    private final ISysRoleService iSysRoleService;
    private final ISysPermissionService iSysPermissionService;

    @Override
    public List<SysRole> list() {
        return iSysRoleService.list();
    }

    @Override
    public List<Tree<String>> permissionBoundTree(Long sysRoleTbId) {
        List<SysPermissionVO> permissionBounds = iSysPermissionService.findBound(sysRoleTbId);
        List<Long> permissionBoundTbIds = permissionBounds.stream().map(SysPermissionVO::getTbId).collect(Collectors.toList());
        List<SysPermission> chinaCities = CollUtil.newArrayList(iSysPermissionService.list());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setParentIdKey("pid");
        treeNodeConfig.setDeep(10);
        return TreeUtil.build(
                chinaCities,
                "-1",
                treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getTbId().toString());
                    tree.setParentId(treeNode.getPid().toString());
                    tree.setName(treeNode.getPermissionName());
                    tree.putExtra("open", true);
                    tree.putExtra("checked", permissionBoundTbIds.contains(treeNode.getTbId()));
                }
        );
    }

    @Override
    public void permissionBind(Long sysRoleTbId, List<Long> sysPermissionBoundTbIds) {
        adminCheck(sysRoleTbId);
        iSysPermissionService.updatePermissionBind(sysRoleTbId, sysPermissionBoundTbIds);
    }

    private void adminCheck(Long sysRoleTbId) {
        Validate.isTrue(!SysRoleEnum.ADMIN.getRoleCode().equals(iSysRoleService.getById(sysRoleTbId).getRoleCode()), StrUtil.format("禁止操作管理员角色[{}]", SysRoleEnum.ADMIN.getRoleCode()));
    }
}
