package net.add1s.ofm;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class OnlineFleaMarketApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(OnlineFleaMarketApplication.class).web(WebApplicationType.SERVLET);
        if (SystemUtils.IS_OS_LINUX) {
            app.build().addListeners(new ApplicationPidFileWriter());
        }
        app.run(args);
    }
}
