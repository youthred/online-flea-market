package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.common.enums.Symbol;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;
import org.apache.commons.lang3.ArrayUtils;
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
        this.cityId = goods.getCityId();
        this.type = goods.getType();
        this.typeCode = goods.getTypeCode();
        this.sellerSysUserTbId = goods.getSellerSysUserTbId();
        this.offShelf = goods.getOffShelf();
        this.deleted = goods.getDeleted();
    }

    public GoodsVO(Goods goods, SysUser seller) {
        this(goods);
        this.seller = new SysUserVO(seller);
    }

    private static final long serialVersionUID = 1035024677737233143L;

    private Long tbId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Double price;
    private String desc;
    private String pics;
    private Long views;
    private Long cityId;
    private String type;
    private String typeCode;
    private Long sellerSysUserTbId;
    private Boolean offShelf;
    private Boolean deleted;

    private String[] picArr;
    private String mainPicUrl;
    private String cityName;
    private String cityExtName;
    private String typeDesc;

    private SysUserVO seller;

    public String[] getPicArr() {
        return StringUtils.isNotBlank(this.pics) ? this.pics.split(Symbol.VERTICAL_BAR.forSplit()) : null;
    }

    public String getMainPicUrl() {
        String[] picArr = getPicArr();
        if (ArrayUtils.isNotEmpty(picArr) && StringUtils.isNotBlank(picArr[0])) {
            return picArr[0];
        } else {
            return "/static/img/goods/default_goods_main.png";
        }
    }
}
