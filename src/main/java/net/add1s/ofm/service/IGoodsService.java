package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.dto.GoodsReportDTO;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsVO;

import java.util.List;

public interface IGoodsService extends IService<Goods> {

    /**
     * 商品详情
     *
     * @param goodsTbId 商品TBID
     * @return GoodsVO
     */
    GoodsVO details(Long goodsTbId);

    /**
     * 搜索全部二手商品
     *
     * @param q 搜索条件
     * @return list GoodsVO
     */
    List<GoodsVO> search(String q);

    /**
     * 浏览量+1
     *
     * @param goodsTbId 商品TBID
     */
    void view(Long goodsTbId);

    /**
     * 举报
     *
     * @param goodsReportDTO 举报请求体
     */
    void report(GoodsReportDTO goodsReportDTO);
}
