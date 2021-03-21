package net.add1s.ofm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

    GoodsVO findGoodsDetail(@Param("goodsTbId") Long goodsTbId);

    List<GoodsVO> findByDesc(@Param("q") String q);

    void viewsAdd(@Param("goodsTbId") Long goodsTbId);

    List<GoodsChatVO> findChatList(@Param("buyerSysUserTbId") Long buyerSysUserTbId, @Param("goodsTbId") Long goodsTbId);

    GoodsChatVO findChat(@Param("buyerSysUserTbId") Long buyerSysUserTbId, @Param("goodsTbId") Long goodsTbId);
}
