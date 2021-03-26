package net.add1s.ofm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 交易角色
 *
 * @author pj.w@qq.com
 */
@Getter
@AllArgsConstructor
public enum TransactionRoleEnum {

    /**
     * 卖家
     */
    SELLER,
    /**
     * 买家
     */
    BUYER
    ;
}
