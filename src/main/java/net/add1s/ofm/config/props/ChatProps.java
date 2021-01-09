package net.add1s.ofm.config.props;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

/**
 * @author pj.w@qq.com
 */
@Configuration
@ConfigurationProperties("sys.chat")
@Data
@Accessors(chain = true)
public class ChatProps {

    @NotBlank
    private Integer port;

    @NotBlank
    private String serverHost;
}
