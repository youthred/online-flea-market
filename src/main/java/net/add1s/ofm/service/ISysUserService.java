package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;

public interface ISysUserService extends IService<SysUser> {

    SysUserVO findSysUserVOByUsername(String username);

    /**
     * 当前访问者是否已登录
     *
     * @return boolean
     */
    boolean isLogin();

    /**
     * 当前登录用户
     *
     * @return MyUserDetails
     */
    MyUserDetails currentUser();
}
