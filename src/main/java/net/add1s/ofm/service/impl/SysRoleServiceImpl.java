package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.common.enums.SysRoleEnum;
import net.add1s.ofm.mapper.SysRoleMapper;
import net.add1s.ofm.pojo.entity.sys.SysRole;
import net.add1s.ofm.pojo.vo.sys.SysBindUserRoleVO;
import net.add1s.ofm.pojo.vo.sys.SysRoleVO;
import net.add1s.ofm.service.ISysRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<String> findRoleCodesByUsername(String username) {
        return this.baseMapper.findRoleCodesByUsername(username);
    }

    @Override
    public List<SysRoleVO> findBySysUserTbId(Long sysUserTbId) {
        return this.baseMapper.findBySysUserTbId(sysUserTbId);
    }

    @Override
    public void setDefaultRole(Long sysUserTbId) {
        SysRole memberRegistered = this.getOne(Wrappers.lambdaQuery(SysRole.class).eq(SysRole::getRoleCode, SysRoleEnum.MEMBER_REGISTERED.getRoleCode()));
        this.baseMapper.setDefaultRole(sysUserTbId, memberRegistered.getTbId());
    }

    @Override
    public List<SysRoleVO> findAllRolesWithUserBound(Long sysUserTbId) {
        List<SysRoleVO> sysRoleVOS = this.list().stream().map(SysRoleVO::new).collect(Collectors.toList());
        List<SysRoleVO> boundRoleVOS = this.findBySysUserTbId(sysUserTbId);
        if (CollectionUtils.isNotEmpty(boundRoleVOS)) {
            sysRoleVOS.forEach(sysRoleVO -> sysRoleVO.setBound(boundRoleVOS.stream().anyMatch(boundRole -> boundRole.getTbId().equals(sysRoleVO.getTbId()))));
        }
        return sysRoleVOS;
    }

    @Override
    public void updateRoleBind(Long sysUserTbId, List<SysRoleVO> sysRoleVOS) {
        this.baseMapper.deleteBounds(sysUserTbId);
        List<SysBindUserRoleVO> sysBindUserRoleVOS = sysRoleVOS.stream().map(sysRoleVO -> new SysBindUserRoleVO(sysUserTbId, sysRoleVO.getTbId())).collect(Collectors.toList());
        this.baseMapper.bindRole(sysBindUserRoleVOS);
    }
}
