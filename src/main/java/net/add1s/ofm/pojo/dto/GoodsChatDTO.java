package net.add1s.ofm.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class GoodsChatDTO implements Serializable {

    private static final long serialVersionUID = -2981649012951408205L;

    /**
     * 商品TBID
     */
    @NotNull
    private Long goodsTbId;
    /**
     * 卖家用户TBID
     */
    @NotNull
    private Long sellerSysUserTbId;
}
