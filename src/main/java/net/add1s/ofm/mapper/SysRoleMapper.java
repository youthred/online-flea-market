package net.add1s.ofm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.add1s.ofm.pojo.entity.sys.SysRole;
import net.add1s.ofm.pojo.vo.sys.SysBindUserRoleVO;
import net.add1s.ofm.pojo.vo.sys.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<String> findRoleCodesByUsername(@Param("username") String username);

    List<SysRoleVO> findBySysUserTbId(@Param("sysUserTbId") Long sysUserTbId);

    void setDefaultRole(@Param("sysUserTbId") Long sysUserTbId, @Param("sysRoleTbId") Long sysRoleTbId);

    void deleteBounds(@Param("sysUserTbId") Long sysUserTbId);

    void bindRole(@Param("sysBindUserRoleVOS") List<SysBindUserRoleVO> sysBindUserRoleVOS);
}
