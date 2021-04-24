package net.add1s.ofm.controller;

import lombok.AllArgsConstructor;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.service.IUserHomeAdminAuthPermissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理 - 权限管理 - 角色
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home/admin/auth/permission")
@AllArgsConstructor
public class UserHomeAdminAuthPermissionController {

    private final IUserHomeAdminAuthPermissionService iUserHomeAdminAuthPermissionService;

    @PostMapping("/page")
    public Res page(@RequestBody @Validated MbpPage<SysPermission> mbpPage) {
        return Res.ok();
    }
}
