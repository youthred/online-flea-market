package net.add1s.ofm.config.auth.impl;

import net.add1s.ofm.pojo.vo.sys.SysPermissionVO;
import net.add1s.ofm.service.ISysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Component("rbacService")
public class RbacService {

    private final ISysPermissionService iSysPermissionService;

    public RbacService(ISysPermissionService iSysPermissionService) {
        this.iSysPermissionService = iSysPermissionService;
    }

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Set<SysPermissionVO> sysPermissionVOS = iSysPermissionService.findSysPermissionVOByUsername(username);
            return sysPermissionVOS.stream().anyMatch(sysPermissionVO ->
                    new AntPathMatcher().match(sysPermissionVO.getPermissionUrl(), request.getRequestURI())
                            && StringUtils.equals(sysPermissionVO.getRequestMethod(), request.getMethod())
            );
        }
        return false;
    }
}
