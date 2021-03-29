package net.add1s.ofm.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.core.util.IdUtil;
import net.add1s.ofm.cache.TimedCacheManager;
import net.add1s.ofm.common.content.SessionContent;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.dto.UserRegisterDTO;
import net.add1s.ofm.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author pj.w@qq.com
 */
@RestController
public class LoginController {

    private final ISysUserService iSysUserService;

    public LoginController(ISysUserService iSysUserService) {
        this.iSysUserService = iSysUserService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index/index");
    }

    @GetMapping("/login.html")
    public ModelAndView loginPage(HttpServletRequest request) {
        return new ModelAndView("sys/auth/login");
    }

    @GetMapping("/register.html")
    public ModelAndView registerPage() {
        return new ModelAndView("sys/auth/register");
    }

    /**
     * 获取GIF图片验证码
     *
     * @param response HttpServletResponse
     * @param session HttpSession
     * @throws IOException e
     */
    @GetMapping("/imageCaptcha")
    public void imageCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        String key = IdUtil.fastSimpleUUID();
        // 验证码缓存的KEY存入当前连接的SESSION
        session.setAttribute(SessionContent.IMAGE_CAPTCHA_KEY, key);
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(200, 100, 4);
        TimedCacheManager.IMAGE_CAPTCHA.put(key, gifCaptcha);
        try (ServletOutputStream out = response.getOutputStream()) {
            gifCaptcha.write(out);
            out.flush();
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @return MyUserDetails
     */
    @GetMapping("/currentLoginUser")
    public Res currentLoginUser() {
        return Res.ok(iSysUserService.currentUser());
    }

    @PostMapping("/register")
    public Res register(@RequestBody @Validated UserRegisterDTO userRegisterDTO, HttpSession session) {
        return Res.ok(iSysUserService.register(userRegisterDTO, session));
    }

    /**
     * 确认新用户合法（验证合法邮箱），验证成功后跳转到提示页面
     *
     * @return 成功后跳转到提示页面
     */
    @GetMapping("/verify/{key}")
    public ModelAndView verify(@PathVariable("key") String key) {
        return new ModelAndView("sys/auth/verify", "verifyResult", iSysUserService.verifyNewUser(key));
    }
}
