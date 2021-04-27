package net.add1s.ofm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pj.w@qq.com
 */
@Getter
@AllArgsConstructor
public enum RequestMethodEnum {

    ANY("ANY"),
    NONE("NONE"),
    ;

    private final String methodName;
}
