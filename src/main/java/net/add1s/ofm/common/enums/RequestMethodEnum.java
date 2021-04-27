package net.add1s.ofm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.add1s.ofm.common.exception.BusinessException;

import java.util.Arrays;

/**
 * @author pj.w@qq.com
 */
@Getter
@AllArgsConstructor
public enum RequestMethodEnum {

    ANY("ANY", "success"),
    NONE("NONE", "secondary"),
    GET("GET", "success"),
    POST("POST", "warning"),
    PUT("PUT", "primary"),
    DELETE("DELETE", "danger"),
    ;

    private final String methodName;
    private final String bootstrapColor;

    public static String parseBootstrapColor(String methodName) {
        return Arrays.stream(values())
                .filter(rm -> rm.methodName.equals(methodName))
                .findFirst()
                .map(RequestMethodEnum::getBootstrapColor)
                .orElseThrow(() -> new BusinessException("未定义的请求方式：" + methodName));
    }
}
