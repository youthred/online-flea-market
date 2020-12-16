package net.add1s.ofm.config.auth.impl;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.common.response.ResponseMessage;
import net.add1s.ofm.common.response.ResponseContentType;
import net.add1s.ofm.factory.StaticFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登录失败响应
 *
 * @author pj.w@qq.com
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(ResponseContentType.JSON_UTF8);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(StaticFactory.objectMapper.writeValueAsString(Res.err(ResponseMessage.LOGIN_FAILED, e)));
        writer.flush();
        writer.close();
        // super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e); // 跳转到请求之前的
    }
}
