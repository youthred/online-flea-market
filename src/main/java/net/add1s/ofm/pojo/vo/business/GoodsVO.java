package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.common.enums.Symbol;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class GoodsVO implements Serializable {

    public GoodsVO(Goods goods) {
        this.tbId = goods.getTbId();
        this.createTime = goods.getCreateTime();
        this.updateTime = goods.getUpdateTime();
        this.price = goods.getPrice();
        this.desc = goods.getDesc();
        this.pics = goods.getPics();
        this.views = goods.getViews();
        this.goodsTypeCode = goods.getGoodsTypeCode();
        this.locationTbId = goods.getLocationTbId();
        this.sellerSysUserTbId = goods.getSellerSysUserTbId();
        this.offShelf = goods.getOffShelf();
    }

    private static final long serialVersionUID = 1035024677737233143L;

    private Long tbId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String price;
    private String desc;
    private String pics;
    private Long views;
    private Short goodsTypeCode;
    private Long locationTbId;
    private Long sellerSysUserTbId;
    private Boolean offShelf;

    private String[] picArr;
    private String goodsTypeDesc;
    private String cityName;
    private String cityExtName;

    private SysUserVO seller;

    public String[] getPicArr() {
        return StringUtils.isNotBlank(this.pics) ? this.pics.split(Symbol.VERTICAL_BAR.forSplit()) : null;
    }
}
