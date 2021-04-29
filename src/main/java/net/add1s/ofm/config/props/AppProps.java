package net.add1s.ofm.config.props;

import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.conf.GithubConf;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
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
    @NestedConfigurationProperty
    private GithubConf github;
    private String javaRuntimeVersion;
    private String recordNumber;
}
