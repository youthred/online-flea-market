package net.add1s.ofm.common.exception;

/**
 * @author pj.w@qq.com
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5585077501520627784L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
