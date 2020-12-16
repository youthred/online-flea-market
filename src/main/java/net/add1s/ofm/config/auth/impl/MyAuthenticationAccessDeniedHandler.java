package net.add1s.ofm.config.auth.impl;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.common.response.ResponseMessage;
import net.add1s.ofm.common.response.ResponseContentType;
import net.add1s.ofm.common.enums.ResponseStatusEnum;
import net.add1s.ofm.factory.StaticFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author pj.w@qq.com
 */
@Component
public class MyAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType(ResponseContentType.JSON_UTF8);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(StaticFactory.objectMapper.writeValueAsString(Res.err(ResponseMessage.RESOURCE_IS_FORBIDDEN, e).setStatus(ResponseStatusEnum._403.getStatus())));
        writer.flush();
        writer.close();
    }
}
