package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class ChinaCityLevel4VO implements Serializable {

    private static final long serialVersionUID = -991114627915373642L;

    private Long tbId;
    private Long id;
    private Long pid;
    private Integer deep;
    private String name;
    private char pinyinPrefix;
    private String pinyin;
    private Long extId;
    private String extName;
}
