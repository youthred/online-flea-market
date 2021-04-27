package net.add1s.ofm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.pojo.vo.sys.SysBindRolePermissionVO;
import net.add1s.ofm.pojo.vo.sys.SysPermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<String> findPermissionCodesByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    Set<SysPermissionVO> findSysPermissionVOByUsername(@Param("username") String username);

    void deleteBounds(@Param("sysRoleTbId") Long sysRoleTbId);

    List<SysPermissionVO> findBound(@Param("sysRoleTbId") Long sysRoleTbId);

    void bindPermission(@Param("sysBindRolePermissionVOS") List<SysBindRolePermissionVO> sysBindRolePermissionVOS);
}
