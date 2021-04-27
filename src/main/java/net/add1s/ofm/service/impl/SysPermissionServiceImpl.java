package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.mapper.SysPermissionMapper;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.pojo.vo.sys.SysBindRolePermissionVO;
import net.add1s.ofm.pojo.vo.sys.SysPermissionVO;
import net.add1s.ofm.service.ISysPermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public List<String> findPermissionCodesByRoleCodes(List<String> roleCodes) {
        return this.baseMapper.findPermissionCodesByRoleCodes(roleCodes);
    }

    @Override
    public Set<SysPermissionVO> findSysPermissionVOByUsername(String username) {
        return this.baseMapper.findSysPermissionVOByUsername(username);
    }

    @Override
    public List<String> findPermitAnyUrl() {
        return this.list(Wrappers.lambdaQuery(SysPermission.class).eq(SysPermission::getPermitAny, true).select(SysPermission::getPermissionUrl))
                .stream()
                .map(SysPermission::getPermissionUrl)
                .collect(Collectors.toList());
    }

    @Override
    public List<SysPermissionVO> findBound(Long sysRoleTbId) {
        return this.baseMapper.findBound(sysRoleTbId);
    }

    @Override
    public void updatePermissionBind(Long sysRoleTbId, List<Long> sysPermissionBoundTbIds) {
        this.baseMapper.deleteBounds(sysRoleTbId);
        if (CollectionUtils.isNotEmpty(sysPermissionBoundTbIds)) {
            List<SysBindRolePermissionVO> sysBindRolePermissionVOS = sysPermissionBoundTbIds.stream().map(permissionBoundTbId -> new SysBindRolePermissionVO(sysRoleTbId, permissionBoundTbId)).collect(Collectors.toList());
            this.baseMapper.bindPermission(sysBindRolePermissionVOS);
        }
    }
}
