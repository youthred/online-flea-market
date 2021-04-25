package net.add1s.ofm.controller;

import lombok.AllArgsConstructor;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysRoleVO;
import net.add1s.ofm.service.IUserHomeAdminUserUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理 - 用户管理 - 用户
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home/admin/user/user")
@AllArgsConstructor
public class UserHomeAdminUserUserController {

    private final IUserHomeAdminUserUserService iUserHomeAdminUserUserService;

    @PostMapping("/page")
    public Res page(@RequestBody @Validated MbpPage<SysUser> mbpPage) {
        return Res.ok(iUserHomeAdminUserUserService.page(mbpPage));
    }

    /**
     * 重置密码
     *
     * @return Res
     */
    @PutMapping("/resetPassword/{sysUserTbId}")
    public Res resetPassword(@PathVariable("sysUserTbId") Long sysUserTbId) {
        iUserHomeAdminUserUserService.resetPassword(sysUserTbId);
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
        iUserHomeAdminUserUserService.enableOrDisable(sysUserTbId, enableState);
        return Res.ok();
    }

    /**
     * 所有角色，已绑定的bound为true
     *
     * @param sysUserTbId 用户TBID
     * @return Res
     */
    @GetMapping("/roles/{sysUserTbId}")
    public Res roles(@PathVariable("sysUserTbId") Long sysUserTbId) {
        return Res.ok(iUserHomeAdminUserUserService.roles(sysUserTbId));
    }

    /**
     * 执行角色绑定
     *
     * @param sysUserTbId 用户TBID
     * @param sysRoleVOS 修改后的绑定状态
     * @return Res
     */
    @PutMapping("/roleBind/{sysUserTbId}")
    public Res roleBind(@PathVariable("sysUserTbId") Long sysUserTbId, @RequestBody List<SysRoleVO> sysRoleVOS) {
        iUserHomeAdminUserUserService.roleBind(sysUserTbId, sysRoleVOS);
        return Res.ok();
    }
}
