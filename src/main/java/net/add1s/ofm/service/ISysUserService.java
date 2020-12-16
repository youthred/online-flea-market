package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;

public interface ISysUserService extends IService<SysUser> {

    SysUserVO findSysUserVOByUsername(String username);
}
