package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.mapper.SysUserMapper;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;
import net.add1s.ofm.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUserVO findSysUserVOByUsername(String username) {
        return this.baseMapper.findSysUserVOByUsername(username);
    }
}
