package net.add1s.ofm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysUserEnum {

    ADMIN("admin"),
    ;

    final private String username;
}
