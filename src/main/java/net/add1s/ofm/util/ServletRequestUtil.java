package net.add1s.ofm.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestUtil {

    /**
     * 匹配请求URI和请求方式
     *
     * @param request HttpServletRequest
     * @param uri 请求URI
     * @param method 请求方式
     * @return 是否匹配
     */
    public static boolean match(HttpServletRequest request, String uri, String method) {
        return StringUtils.equals(request.getRequestURI(), uri) && StringUtils.equals(request.getMethod(), method);
    }
}
