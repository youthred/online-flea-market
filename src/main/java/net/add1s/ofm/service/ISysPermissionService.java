package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.pojo.vo.sys.SysPermissionVO;

import java.util.List;
import java.util.Set;

public interface ISysPermissionService extends IService<SysPermission> {

    List<String> findPermissionCodesByRoleCodes(List<String> roleCodes);

    Set<SysPermissionVO> findSysPermissionVOByUsername(String username);

    /**
     * 查询出无需权限的接口路径
     *
     * @return String list
     */
    List<String> findPermitAnyUrl();

    /**
     * 查询所有已绑定的权限信息
     *
     * @param sysRoleTbId 角色TBID
     * @return SysPermissionVOS
     */
    List<SysPermissionVO> findBound(Long sysRoleTbId);

    /**
     * 修改权限绑定
     *
     * @param sysRoleTbId 角色TBID
     * @param sysPermissionBoundTbIds 修改后的绑定权限TBID
     */
    void updatePermissionBind(Long sysRoleTbId, List<Long> sysPermissionBoundTbIds);
}
