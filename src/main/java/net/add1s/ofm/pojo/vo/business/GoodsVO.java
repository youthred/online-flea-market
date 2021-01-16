package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class GoodsVO implements Serializable {

    private static final long serialVersionUID = 1035024677737233143L;

    private Long tbId;
    private String name;
    private String price;
    private String desc;
    private String pics;
    private Long views;
    private Long goodsTypeCode;
    private Long locationTbId;
    private Long sysUserTbId;
    private Boolean offShelf;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
