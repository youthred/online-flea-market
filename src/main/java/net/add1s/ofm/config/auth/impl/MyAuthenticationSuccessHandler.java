package net.add1s.ofm.config.auth.impl;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.common.response.ResponseMessage;
import net.add1s.ofm.common.response.ResponseContentType;
import net.add1s.ofm.factory.StaticFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登录成功响应
 *
 * @author pj.w@qq.com
 */
@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType(ResponseContentType.JSON_UTF8);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(StaticFactory.objectMapper.writeValueAsString(Res.ok().setMessage(ResponseMessage.LOGIN_SUCCESS)));
        writer.flush();
        writer.close();
        // super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication); // 跳转到打开之前请求的页面
    }
}
