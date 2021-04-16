package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;

import java.io.Serializable;
import java.util.Objects;

/**
 * 我卖出的
 *
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SoldOrBoughtVO implements Serializable {

    private static final long serialVersionUID = 8249534064272700925L;

    public SoldOrBoughtVO(Goods goods, SysUser seller) {
        this.goods = new GoodsVO(goods, seller);
    }

    public SoldOrBoughtVO(Goods goods, SysUser seller, SysUser buyer) {
        this.goods = new GoodsVO(goods, seller);
        this.buyer = new SysUserVO(buyer);
    }

    private GoodsVO goods;
    private SysUserVO buyer;

    public GoodsVO getGoods() {
        if (Objects.nonNull(this.goods) && Objects.nonNull(this.goods.getSeller())) {
            this.goods.getSeller().setPassword(null);
        }
        return this.goods;
    }

    public SysUserVO getBuyer() {
        if (Objects.nonNull(this.buyer)) {
            this.buyer.setPassword(null);
        }
        return this.buyer;
    }
}
