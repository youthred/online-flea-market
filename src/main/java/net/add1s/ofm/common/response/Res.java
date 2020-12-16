package net.add1s.ofm.common.response;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.common.enums.ResponseStatusEnum;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class Res implements Serializable {

    private static final long serialVersionUID = 7066828830737937054L;

    private Integer status;
    private Boolean success;
    private String message;
    private Object data;

    public static Res me() {
        return new Res();
    }

    public static Res ok() {
        return new Res().setStatus(ResponseStatusEnum._200.getStatus()).setSuccess(Boolean.TRUE).setMessage(ResponseStatusEnum._200.getMessage());
    }

    public static Res ok(Object data) {
        return ok().setData(data);
    }

    public static Res err() {
        return new Res().setStatus(ResponseStatusEnum._500.getStatus()).setSuccess(Boolean.FALSE).setMessage(ResponseStatusEnum._500.getMessage());
    }

    public static Res err(String message) {
        return err().setMessage(message);
    }

    public static Res err(String title, String message) {
        return err().setMessage(title + ": " + message);
    }

    public static Res err(String title, Throwable e) {
        var ref = new Object() {
            Res err = err(title);
        };
        Optional.of(e).ifPresent(ex -> ref.err = err(title + ": " + ExceptionUtil.getSimpleMessage(e)));
        return ref.err;
    }
}
