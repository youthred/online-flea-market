package net.add1s.ofm.service.impl;

import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.cache.TimedCacheManager;
import net.add1s.ofm.common.content.SessionContent;
import net.add1s.ofm.config.auth.MyPasswordEncoder;
import net.add1s.ofm.config.props.WebProps;
import net.add1s.ofm.mapper.SysUserMapper;
import net.add1s.ofm.pojo.dto.UserRegisterDTO;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.business.VerifyResult;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;
import net.add1s.ofm.service.ISysUserService;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final WebProps webProps;
    private final MyPasswordEncoder myPasswordEncoder;

    public SysUserServiceImpl(WebProps webProps,
                              MyPasswordEncoder myPasswordEncoder) {
        this.webProps = webProps;
        this.myPasswordEncoder = myPasswordEncoder;
    }

    @Override
    public SysUserVO findSysUserVOByUsername(String username) {
        return this.baseMapper.findSysUserVOByUsername(username);
    }

    @Override
    public boolean isLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication)
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    @Override
    public MyUserDetails currentUser() {
        Validate.isTrue(this.isLogin(), "当前访问者未登录");
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String register(UserRegisterDTO userRegisterDTO, HttpSession session) {
        registerValidate(userRegisterDTO, session);
        SysUser sysUser = userRegisterDTO.toEntity();
        sysUser.setPassword(myPasswordEncoder.encode(sysUser.getPassword()));
        this.save(sysUser);
        // todo 发邮件确认（启用）用户，这里模拟已经发送
        final String verifyLinkPrefix = webProps.getHost() + "/verify/";
        String key = IdUtil.fastSimpleUUID();
        TimedCacheManager.NEW_USER_VERIFY.put(key, sysUser.getTbId());
        return verifyLinkPrefix + key;
    }

    private void registerValidate(UserRegisterDTO userRegisterDTO, HttpSession session) {
        String key = (String) session.getAttribute(SessionContent.IMAGE_CAPTCHA_KEY);
        ICaptcha iCaptcha = Optional.ofNullable(TimedCacheManager.IMAGE_CAPTCHA.get(key, false)).orElseThrow(() -> new SessionAuthenticationException("验证码已过期，请重新获取"));
        Validate.isTrue(iCaptcha.verify(userRegisterDTO.getImageCaptcha()), "验证码错误");
        Validate.isTrue(this.count(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getEmail, userRegisterDTO.getEmail())) == 0, "邮箱已存在");
        Validate.isTrue(this.count(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, userRegisterDTO.getUsername())) == 0, "用户名已存在");
        Validate.isTrue(this.count(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getNickname, userRegisterDTO.getNickname())) == 0, "昵称已存在");
    }

    @Override
    public VerifyResult verifyNewUser(String key) {
        try {
            // 获取缓存中的key并不再维护该key
            Long userTbId = TimedCacheManager.NEW_USER_VERIFY.get(key, false);
            if (Objects.nonNull(userTbId)) {
                boolean isVerify = this.update(Wrappers.lambdaUpdate(SysUser.class).set(SysUser::getEnabled, true).eq(SysUser::getTbId, userTbId));
                return new VerifyResult().setSuccess(isVerify).setMessage(isVerify ? null : "请联系管理员");
            } else {
                return new VerifyResult().setSuccess(false).setMessage("验证链接已过期或不存在，注册信息将在过期后的10分钟内删除，请重新注册并及时进行验证");
            }
        } catch (Exception e) {
            String stackTrace = ExceptionUtils.getStackTrace(e);
            log.error(stackTrace);
            return new VerifyResult().setSuccess(false).setMessage("Exception: " + stackTrace);
        }
    }
}
