package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.dto.GoodsDTO;
import net.add1s.ofm.pojo.dto.GoodsReportDTO;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;

import java.util.List;

public interface IGoodsService extends IService<Goods> {

    /**
     * 已上架商品详情
     *
     * @param goodsTbId 商品TBID
     * @return GoodsVO
     */
    GoodsVO detailOfOnShelf(Long goodsTbId);

    /**
     * 商品详情（不论是否下架）
     *
     * @param goodsTbId 商品TBID
     * @return GoodsVO
     */
    GoodsVO detail(Long goodsTbId);

    /**
     * 商品分页（仅已上架）
     *
     * @param mbpPage MbpPage
     * @return goods page
     */
    IPage<Goods> goodsPage(MbpPage<Goods> mbpPage);

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

    /**
     * 私聊列表
     *
     * @return GoodsChatVO list
     */
    List<GoodsChatVO> chats();

    /**
     * 私聊
     *
     * @param goodsTbId 商品TBID
     * @return GoodsChatVO
     */
    GoodsChatVO chat(Long goodsTbId);

    /**
     * 下架商品
     *
     * @param goodsTbId 商品TBID
     */
    void offShelf(Long goodsTbId);

    /**
     * 重新上架商品
     *
     * @param goodsTbId 商品TBID
     */
    void onShelf(Long goodsTbId);

    /**
     * 新增闲置
     *
     * @param goodsDTO GoodsDTO
     */
    void saveNewGoods(GoodsDTO goodsDTO);

    /**
     * 更新商品
     *
     * @param goodsDTO GoodsDTO
     */
    void updateGoods(GoodsDTO goodsDTO);
}
