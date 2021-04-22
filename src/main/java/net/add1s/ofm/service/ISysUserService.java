package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.dto.UserRegisterDTO;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.business.VerifyResult;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;

import javax.servlet.http.HttpSession;

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

    /**
     * 幽灵用户：方便系统使用
     *
     * @return MyUserDetails
     */
    MyUserDetails ghostUser();

    /**
     * 用户注册
     *
     * @param userRegisterDTO UserRegisterDTO
     * @param session
     */
    String register(UserRegisterDTO userRegisterDTO, HttpSession session);

    /**
     * 新用户启用确认
     *
     * @param key 缓存KEY
     */
    VerifyResult verifyNewUser(String key);
}
