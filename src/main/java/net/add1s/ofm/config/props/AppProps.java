package net.add1s.ofm.config.props;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author pj.w@qq.com
 */
@Configuration
@ConfigurationProperties("sys.app")
@Data
@Accessors(chain = true)
public class AppProps {

    private String buildTime;
    private String projectVersion;
    private String projectName;
    private String projectDescription;
    private String github;
    private String javaRuntimeVersion;
}
