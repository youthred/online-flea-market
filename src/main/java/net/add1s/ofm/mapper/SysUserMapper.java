package net.add1s.ofm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUserVO findSysUserVOByUsername(@Param("username") String username);
}
