package net.add1s.ofm.config.auth.impl;

import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.exceptions.ExceptionUtil;
import net.add1s.ofm.cache.TimedCacheManager;
import net.add1s.ofm.common.content.SessionContent;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.util.ServletRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * @author pj.w@qq.com
 */
@Component
public class LoginFilter extends OncePerRequestFilter {

    public final String uri = "/login";
    public final String method = RequestMethod.POST.name();
    public final String captchaCodeName = "imageCaptcha";
    public final String username = "username";

    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final ISysUserService iSysUserService;

    public LoginFilter(MyAuthenticationFailureHandler myAuthenticationFailureHandler,
                       ISysUserService iSysUserService) {
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.iSysUserService = iSysUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 若为POST登录请求
        if (ServletRequestUtil.match(httpServletRequest, uri, method)) {
            try {
                // 重复登录判断
                if (iSysUserService.isLogin()) {
                    MyUserDetails userDetails = iSysUserService.currentUser();
                    if (StringUtils.equals(userDetails.getUsername(), ServletRequestUtils.getStringParameter(httpServletRequest, username))) {
                        throw new AuthenticationServiceException("您已登录，请勿重复操作。");
                    }
                }
                // 图形验证码校验
                validate(new ServletWebRequest(httpServletRequest));
            } catch (Exception e) {
                myAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, new SessionAuthenticationException(ExceptionUtil.getSimpleMessage(e)));
                return;
            }
        }
        // 放过进行之后的账户密码验证
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        HttpSession session = servletWebRequest.getRequest().getSession();
        // 表单图形验证码的值
        String captchaCode = Optional.ofNullable(ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), captchaCodeName)).filter(StringUtils::isNotBlank).orElseThrow(() -> new SessionAuthenticationException("验证码为空。"));
        // 缓存KEY
        String key = (String) session.getAttribute(SessionContent.IMAGE_CAPTCHA_KEY);
        // 图形验证码
        ICaptcha iCaptcha = Optional.ofNullable(TimedCacheManager.IMAGE_CAPTCHA.get(key, false)).orElseThrow(() -> new SessionAuthenticationException("验证码已过期，请重新获取。"));
        Validate.isTrue(iCaptcha.verify(captchaCode), "验证不通过。");
    }
}
