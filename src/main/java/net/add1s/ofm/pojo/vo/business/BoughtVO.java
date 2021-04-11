package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;

import java.io.Serializable;

/**
 * 我买到的
 *
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BoughtVO implements Serializable {

    private static final long serialVersionUID = 6679907699866027483L;

    public BoughtVO(Goods goods, SysUser sysUser) {
        this.goods = new GoodsVO(goods);
        this.seller = new SysUserVO(sysUser);
    }

    private GoodsVO goods;
    private SysUserVO seller;
}
