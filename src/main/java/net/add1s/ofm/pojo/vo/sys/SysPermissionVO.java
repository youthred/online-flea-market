package net.add1s.ofm.pojo.vo.sys;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class SysPermissionVO implements Serializable {

    private static final long serialVersionUID = -7536517957653912221L;

    private Long tbId;
    private String permissionUrl;
    private String requestMethod;
    private String permissionCode;
    private String permissionName;
    private String permissionDesc;
    private Long id;
    private Long pid;
}
