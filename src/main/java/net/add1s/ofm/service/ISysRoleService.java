package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.sys.SysRole;
import net.add1s.ofm.pojo.vo.sys.SysRoleVO;

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

    /**
     * 查找所有角色，已绑定的bound为true
     *
     * @param sysUserTbId 用户TBID
     * @return SysRoleVO list
     */
    List<SysRoleVO> findAllRolesWithUserBound(Long sysUserTbId);

    /**
     * 修改角色绑定
     *
     * @param sysUserTbId 用户TBID
     * @param sysRoleVOS 更改后的角色绑定信息
     */
    void updateRoleBind(Long sysUserTbId, List<SysRoleVO> sysRoleVOS);
}
