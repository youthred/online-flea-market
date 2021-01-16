package net.add1s.ofm.config.web;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component()
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")   // 文件存放位置
                .addResourceLocations("classpath:/static/");    // 对外暴露路径
        super.addResourceHandlers(registry);
    }

    /**
     * 自定义String转LocalDateTime方法，此方法将会作用于url所携带的参数上
     */
    static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String s) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
            return LocalDateTime.parse(s, formatter);
        }
    }

    /**
     * 将上述自定义方法进行添加
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter());
    }

    /**
     * 增加序列化与反序列化器，它们将作用于实体类的LocalDateTime属性。
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(pattern));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(pattern));
        objectMapper.registerModule(module);
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
