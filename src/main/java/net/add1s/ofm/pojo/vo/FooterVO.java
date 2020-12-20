package net.add1s.ofm.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class FooterVO implements Serializable {

    private static final long serialVersionUID = 2377889344091305861L;

    private String javaVersion;
    private String projectVersion;
    private String projectName;
    private String projectDescription;
    private String github;
}
