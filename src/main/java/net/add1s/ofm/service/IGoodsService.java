package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsVO;

public interface IGoodsService extends IService<Goods> {

    /**
     * 商品详情
     *
     * @param goodsTbId 商品TBID
     * @return GoodsVO
     */
    GoodsVO details(Long goodsTbId);
}
