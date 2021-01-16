package net.add1s.ofm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pj.w@qq.com
 */

@Getter
@AllArgsConstructor
public enum QueryTypeEnum {

    /**
     * equal 等于
     */
    eq,
    /**
     * not equal 不等于
     */
    ne,
    /**
     * greater than 大于
     */
    gt,
    /**
     * less than 小于
     */
    lt,
    /**
     * greater than or equal 大于等于
     */
    ge,
    /**
     * less than or equal 小于等于
     */
    le,
    /**
     * like 模糊匹配
     */
    like,
    ;
}
