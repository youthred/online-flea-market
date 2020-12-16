package net.add1s.ofm.config;

import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.common.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author pj.w@qq.com
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param request HttpServletRequest
     * @param e BusinessException
     * @return Res
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Res businessExceptionHandler(HttpServletRequest request, BusinessException e){
        log.error("业务异常：{}", e.getCause().getMessage());
        return Res.err(e.getCause().getMessage());
    }

    /**
     * 其他异常
     *
     * @param request HttpServletRequest
     * @param e Exception
     * @return Res
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Res exceptionHandler(HttpServletRequest request, Exception e){
        log.error("服务端错误：{}", e.getCause().getMessage());
        return Res.err(e.getCause().getMessage());
    }
}
