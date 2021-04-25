package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.common.enums.SysRoleEnum;
import net.add1s.ofm.mapper.SysRoleMapper;
import net.add1s.ofm.pojo.entity.sys.SysRole;
import net.add1s.ofm.service.ISysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<String> findRoleCodesByUsername(String username) {
        return this.baseMapper.findRoleCodesByUsername(username);
    }

    @Override
    public List<SysRole> findBySysUserTbId(Long sysUserTbId) {
        return this.baseMapper.findBySysUserTbId(sysUserTbId);
    }

    @Override
    public void setDefaultRole(Long sysUserTbId) {
        SysRole memberRegistered = this.getOne(Wrappers.lambdaQuery(SysRole.class).eq(SysRole::getRoleCode, SysRoleEnum.MEMBER_REGISTERED.getRoleName()));
        this.baseMapper.setDefaultRole(sysUserTbId, memberRegistered.getTbId());
    }
}
