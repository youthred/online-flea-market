package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.sys.SysRole;

import java.util.List;

public interface ISysRoleService extends IService<SysRole> {

    List<String> findRoleCodesByUsername(String username);

    List<SysRole> findBySysUserTbId(Long sysUserTbId);

    /**
     * 添加默认角色
     *
     * @param sysUserTbId 用户TBID
     */
    void setDefaultRole(Long sysUserTbId);
}
