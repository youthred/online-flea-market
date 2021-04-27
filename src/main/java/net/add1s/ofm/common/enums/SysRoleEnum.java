package net.add1s.ofm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.add1s.ofm.common.content.AuthContent;

/**
 * @author pj.w@qq.com
 */
@Getter
@AllArgsConstructor
public enum SysRoleEnum {

    ADMIN("ADMIN"),
    MEMBER_REGISTERED("MEMBER_REGISTERED"),
    ;

    private final String roleCode;

    public String forRolePrefix() {
        return AuthContent.ROLE_PREFIX + this.roleCode;
    }
}
