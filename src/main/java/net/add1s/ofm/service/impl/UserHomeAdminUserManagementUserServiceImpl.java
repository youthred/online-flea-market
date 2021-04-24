package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.config.auth.MyPasswordEncoder;
import net.add1s.ofm.config.props.SecurityProps;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.service.IUserHomeAdminUserManagementUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UserHomeAdminUserManagementUserServiceImpl implements IUserHomeAdminUserManagementUserService {

    private final ISysUserService iSysUserService;
    private final MyPasswordEncoder myPasswordEncoder;
    private final SecurityProps securityProps;

    @Override
    public IPage<SysUser> page(MbpPage<SysUser> mbpPage) {
        return iSysUserService.page(mbpPage.getPage(), mbpPage.toQueryWrapper());
    }

    @Override
    public void resetPassword(Long sysUserTbId) {
        iSysUserService.update(
                Wrappers.lambdaUpdate(SysUser.class)
                        .eq(SysUser::getTbId, sysUserTbId)
                        .set(SysUser::getUpdateTime, LocalDateTime.now())
                        .set(SysUser::getPassword, myPasswordEncoder.encode(securityProps.getDefaultPassword()))
        );
    }

    @Override
    public void enableOrDisable(Long sysUserTbId, Boolean enableState) {
        iSysUserService.update(
                Wrappers.lambdaUpdate(SysUser.class)
                        .set(SysUser::getUpdateTime, LocalDateTime.now())
                        .eq(SysUser::getTbId, sysUserTbId).set(SysUser::getEnabled, enableState)
        );
    }
}
