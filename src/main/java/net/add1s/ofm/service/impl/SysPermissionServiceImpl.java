package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.mapper.SysPermissionMapper;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.pojo.vo.sys.SysPermissionVO;
import net.add1s.ofm.service.ISysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
}
