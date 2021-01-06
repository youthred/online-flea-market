package net.add1s.ofm.config.auth.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.add1s.ofm.common.content.AuthContent;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.service.ISysPermissionService;
import net.add1s.ofm.service.ISysRoleService;
import net.add1s.ofm.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Transactional(rollbackFor = Exception.class)
public class MyUserDetailsService implements UserDetailsService {

    private final ISysUserService iSysUserService;
    private final ISysRoleService iSysRoleService;
    private final ISysPermissionService iSysPermissionService;

    public MyUserDetailsService(
            ISysUserService iSysUserService,
            ISysRoleService iSysRoleService,
            ISysPermissionService iSysPermissionService
    ) {
        this.iSysUserService = iSysUserService;
        this.iSysRoleService = iSysRoleService;
        this.iSysPermissionService = iSysPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Validate.isTrue(StringUtils.isNotBlank(username), "用户名为空");
        return getUserDetailsByUsername(username);
    }

    private MyUserDetails getUserDetailsByUsername(String username) {
        SysUser sysUser = iSysUserService.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, username));
        Validate.isTrue(Objects.nonNull(sysUser), StrUtil.format("用户 [{}] 不存在", username));
        Validate.isTrue(sysUser.getEnabled(), StrUtil.format("用户 [{}] 未启用", username));

        List<String> roleCodes = iSysRoleService.findRoleCodesByUsername(username);
        // 先添加权限
        List<String> authorities = iSysPermissionService.findPermissionCodesByRoleCodes(roleCodes);
        // 再添加角色(带"ROLE_"前缀的特殊权限)
        authorities.addAll(roleCodes.stream().map(roleCode -> StringUtils.join(AuthContent.ROLE_PREFIX, roleCode)).collect(Collectors.toList()));

        return new MyUserDetails()
                .setTbId(sysUser.getTbId())
                .setNickname(sysUser.getNickname())
                .setUsername(sysUser.getUsername())
                .setPassword(sysUser.getPassword())
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setEnabled(sysUser.getEnabled())
                .setAuthorities(authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
