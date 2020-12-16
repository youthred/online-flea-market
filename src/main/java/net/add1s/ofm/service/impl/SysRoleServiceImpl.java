package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
