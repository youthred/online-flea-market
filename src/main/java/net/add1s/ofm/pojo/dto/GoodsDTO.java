package net.add1s.ofm.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class GoodsDTO implements Serializable {

    private static final long serialVersionUID = -3350624601743812820L;

    private Long tbId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String desc;
    private String pics;
    private double price;
    private long locationTbId;
    private Long sellerSysUserTbId;
    private boolean offShelf;
}
