package net.add1s.ofm.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.business.Goods;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank
    private String desc;
    @NotBlank
    private String pics;
    @Min(0)
    private double price;
    @NotNull
    private Long cityId;
    private Long sellerSysUserTbId;
    private boolean offShelf;

    public Goods toEntity() {
        return new Goods()
                .setTbId(this.tbId)
                .setCreateTime(this.createTime)
                .setUpdateTime(this.updateTime)
                .setDesc(this.desc)
                .setPics(this.pics)
                .setPrice(this.price)
                .setCityId(this.cityId)
                .setSellerSysUserTbId(this.sellerSysUserTbId)
                .setOffShelf(this.offShelf);
    }
}
