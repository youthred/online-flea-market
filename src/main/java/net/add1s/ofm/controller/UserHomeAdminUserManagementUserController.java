package net.add1s.ofm.controller;

import lombok.AllArgsConstructor;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.service.IUserHomeAdminUserManagementUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统管理 - 用户管理 - 用户
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home/admin/userManagement/user")
@AllArgsConstructor
public class UserHomeAdminUserManagementUserController {

    private final IUserHomeAdminUserManagementUserService iUserHomeAdminUserManagementUserService;

    @PostMapping("/page")
    public Res page(@RequestBody @Validated MbpPage<SysUser> mbpPage) {
        return Res.ok(iUserHomeAdminUserManagementUserService.page(mbpPage));
    }

    /**
     * 重置密码
     *
     * @return Res
     */
    @PutMapping("/resetPassword/{sysUserTbId}")
    public Res resetPassword(@PathVariable("sysUserTbId") Long sysUserTbId) {
        iUserHomeAdminUserManagementUserService.resetPassword(sysUserTbId);
        return Res.ok();
    }

    /**
     * 启用或禁用
     *
     * @param sysUserTbId 用户TBID
     * @param enableState 启用状态
     * @return Res
     */
    @PutMapping("/enableOrDisable/{sysUserTbId}/{enableState}")
    public Res enableOrDisable(@PathVariable("sysUserTbId") Long sysUserTbId, @PathVariable("enableState") Boolean enableState) {
        iUserHomeAdminUserManagementUserService.enableOrDisable(sysUserTbId, enableState);
        return Res.ok();
    }
}
