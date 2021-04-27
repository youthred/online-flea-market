package net.add1s.ofm.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.add1s.ofm.common.enums.SysUserEnum;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.config.auth.MyPasswordEncoder;
import net.add1s.ofm.config.props.SecurityProps;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysRoleVO;
import net.add1s.ofm.service.ISysRoleService;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.service.IUserHomeAdminUserUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UserHomeAdminUserUserServiceImpl implements IUserHomeAdminUserUserService {

    private final ISysUserService iSysUserService;
    private final MyPasswordEncoder myPasswordEncoder;
    private final SecurityProps securityProps;
    private final ISysRoleService iSysRoleService;

    @Override
    public IPage<SysUser> page(MbpPage<SysUser> mbpPage) {
        return iSysUserService.page(mbpPage.getPage(), mbpPage.toQueryWrapper());
    }

    @Override
    public void resetPassword(Long sysUserTbId) {
        adminCheck(sysUserTbId);
        iSysUserService.update(
                Wrappers.lambdaUpdate(SysUser.class)
                        .eq(SysUser::getTbId, sysUserTbId)
                        .set(SysUser::getUpdateTime, LocalDateTime.now())
                        .set(SysUser::getPassword, myPasswordEncoder.encode(securityProps.getDefaultPassword()))
        );
    }

    @Override
    public void enableOrDisable(Long sysUserTbId, Boolean enableState) {
        adminCheck(sysUserTbId);
        iSysUserService.update(
                Wrappers.lambdaUpdate(SysUser.class)
                        .set(SysUser::getUpdateTime, LocalDateTime.now())
                        .eq(SysUser::getTbId, sysUserTbId).set(SysUser::getEnabled, enableState)
        );
    }

    @Override
    public List<SysRoleVO> roles(Long sysUserTbId) {
        return iSysRoleService.findAllRolesWithUserBound(sysUserTbId);
    }

    @Override
    public void roleBind(Long sysUserTbId, List<SysRoleVO> sysRoleVOS) {
        adminCheck(sysUserTbId);
        List<SysRoleVO> roleBindUpdateList = sysRoleVOS.stream().filter(SysRoleVO::isBound).collect(Collectors.toList());
        Validate.isTrue(CollectionUtils.isNotEmpty(roleBindUpdateList), "请至少绑定一个角色");
        iSysRoleService.updateRoleBind(sysUserTbId, roleBindUpdateList);
    }

    private void adminCheck(Long sysUserTbId) {
        Validate.isTrue(!SysUserEnum.ADMIN.getUsername().equals(iSysUserService.getById(sysUserTbId).getUsername()), StrUtil.format("禁止操作初始用户[{}]", SysUserEnum.ADMIN.getUsername()));
    }
}
