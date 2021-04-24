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
@ConfigurationProperties("sys.security")
@Data
@Accessors(chain = true)
public class SecurityProps {

    @NotBlank
    private String salt;

    @NotBlank
    private String defaultPassword;
}
