package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class VerifyResult implements Serializable {

    private static final long serialVersionUID = -1651643791165740935L;

    private boolean success;
    private String message;
}
