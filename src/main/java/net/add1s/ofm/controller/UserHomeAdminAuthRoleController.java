package net.add1s.ofm.controller;

import lombok.AllArgsConstructor;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.service.IUserHomeAdminAuthRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理 - 权限管理 - 角色
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home/admin/auth/role")
@AllArgsConstructor
public class UserHomeAdminAuthRoleController {

    private final IUserHomeAdminAuthRoleService iUserHomeAdminAuthRoleService;

    @GetMapping("/list")
    public Res list() {
        return Res.ok(iUserHomeAdminAuthRoleService.list());
    }

    @GetMapping("/permissionBoundTree/{sysRoleTbId}")
    public Res permissionBoundTree(@PathVariable("sysRoleTbId") Long sysRoleTbId) {
        return Res.ok(iUserHomeAdminAuthRoleService.permissionBoundTree(sysRoleTbId));
    }

    @PutMapping("/permissionBind/{sysRoleTbId}")
    public Res permissionBind(@PathVariable("sysRoleTbId") Long sysRoleTbId, @RequestBody List<Long> sysPermissionBoundTbIds) {
        iUserHomeAdminAuthRoleService.permissionBind(sysRoleTbId, sysPermissionBoundTbIds);
        return Res.ok();
    }
}
