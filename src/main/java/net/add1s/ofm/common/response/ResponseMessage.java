package net.add1s.ofm.common.response;

/**
 * @author pj.w@qq.com
 */
public class ResponseMessage {

    public static final String OPERATION_SUCCESS = "请求成功";
    public static final String OPERATION_FAILED = "请求失败";
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String LOGIN_FAILED = "登录失败";
    public static final String LOGOUT_SUCCESS = "登出成功";

    public static final String OFFLINE_REMINDER_SESSION_EXPIRED = "您已在其他设备登录，本会话结束。";
    public static final String OFFLINE_REMINDER_SESSION_INVALID = "无效SESSION，请重新登录。";

    public static final String RESOURCE_IS_FORBIDDEN = "资源禁止访问";
}
