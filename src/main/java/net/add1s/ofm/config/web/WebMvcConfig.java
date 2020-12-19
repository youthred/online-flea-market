package net.add1s.ofm.config.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")   // 文件存放位置
                .addResourceLocations("classpath:/static/");    // 对外暴露路径
        super.addResourceHandlers(registry);
    }
}
