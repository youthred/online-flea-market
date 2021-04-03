package net.add1s.ofm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取已上架商品详情
     *
     * @param goodsTbId 商品TBID
     * @return GoodsVO
     */
    GoodsVO findOnShelfGoodsDetailByTbId(@Param("goodsTbId") Long goodsTbId);

    /**
     * 获取商品详情（不论是否下架）
     *
     * @param goodsTbId 商品TBID
     * @return GoodsVO
     */
    GoodsVO findGoodsDetailByTbId(@Param("goodsTbId") Long goodsTbId);

    void viewsAdd(@Param("goodsTbId") Long goodsTbId);

    List<GoodsChatVO> findChatList(@Param("currentUserTbId") Long currentUserTbId);
}
