package net.add1s.ofm.config.auth.impl;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.common.response.ResponseMessage;
import net.add1s.ofm.common.response.ResponseContentType;
import net.add1s.ofm.factory.StaticFactory;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author pj.w@qq.com
 */
@Component
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        sessionInformationExpiredEvent.getResponse().setContentType(ResponseContentType.JSON_UTF8);
        PrintWriter writer = sessionInformationExpiredEvent.getResponse().getWriter();
        writer.write(StaticFactory.objectMapper.writeValueAsString(Res.err(ResponseMessage.OFFLINE_REMINDER_SESSION_EXPIRED)));
        writer.flush();
        writer.close();
    }
}
