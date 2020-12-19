package net.add1s.ofm.controller.sys;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.cache.TimedCacheManager;
import net.add1s.ofm.common.content.SessionContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author pj.w@qq.com
 */
@Controller
@Slf4j
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "index/index";
    }

    @GetMapping("/login.html")
    public String loginPage(HttpServletRequest request) {
        return "sys/auth/login";
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
        session.setAttribute(SessionContent.IMAGE_CAPTCHA_KEY, key);
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(200, 100, 4);
        TimedCacheManager.IMAGE_CAPTCHA.put(key, gifCaptcha);
        try (ServletOutputStream out = response.getOutputStream()) {
            gifCaptcha.write(out);
            out.flush();
        }
    }
}
