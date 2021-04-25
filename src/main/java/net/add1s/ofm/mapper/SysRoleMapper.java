package net.add1s.ofm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.add1s.ofm.pojo.entity.sys.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<String> findRoleCodesByUsername(@Param("username") String username);

    List<SysRole> findBySysUserTbId(@Param("sysUserTbId") Long sysUserTbId);

    void setDefaultRole(@Param("sysUserTbId") Long sysUserTbId, @Param("sysRoleTbId") Long sysRoleTbId);
}
