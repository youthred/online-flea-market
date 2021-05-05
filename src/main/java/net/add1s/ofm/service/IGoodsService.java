package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.dto.GoodsDTO;
import net.add1s.ofm.pojo.dto.GoodsReportDTO;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import net.add1s.ofm.pojo.vo.business.ParameterVO;

import java.util.List;

public interface IGoodsService extends IService<Goods> {

    /**
     * 二手书分类
     *
     * @return ParameterVOS
     */
    List<ParameterVO> usedBookTypes();

    /**
     * 已上架商品详情
     *
     * @param goodsTbId 商品TBID
     * @return GoodsVO
     */
    GoodsVO detailOfOnShelfAndUnDeleted(Long goodsTbId);

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
    IPage<GoodsVO> goodsPage(MbpPage<Goods> mbpPage);

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
     * @param buyerSysUserTbId 买家TBID
     * @param sellerSysUserTbId 卖家TBID
     * @return GoodsChatVO
     */
    GoodsChatVO chat(Long goodsTbId, Long buyerSysUserTbId, Long sellerSysUserTbId);

    /**
     * 下架商品
     *
     * @param goodsTbId 商品TBID
     * @param checkAuth 是否检查权限
     */
    void offShelf(Long goodsTbId, boolean checkAuth);

    /**
     * 重新上架商品
     *
     * @param goodsTbId 商品TBID
     * @param checkAuth 是否检查权限
     */
    void onShelf(Long goodsTbId, boolean checkAuth);

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

    /**
     * 删除商品，更新为[下架、已删除]
     *
     * @param goodsTbId 商品TBID
     * @param checkAuth 是否检查权限
     */
    void deleteGoods(Long goodsTbId, boolean checkAuth);

    /**
     * 商品购买
     *
     * @param goodsTbId 商品TBID
     */
    void buy(Long goodsTbId);/**

     * 当前用户是否允许操作某一商品，仅未卖商品时的超级管理员或商品卖家允许操作
     *
     * @param goodsTbId 商品TBID
     * @return 是否允许
     */
    boolean isAllowedToOperateGoods(Long goodsTbId);

    /**
     * 执行下架操作
     * 若当前用户非管理员，请务必先进行权限验证(isAllowedToOperateGoods)
     *
     * @param goodsTbId 商品TBID
     */
    void doOffShelf(Long goodsTbId);

    /**
     * 执行上架操作
     * 若当前用户非管理员，请务必先进行权限验证(isAllowedToOperateGoods)
     *
     * @param goodsTbId 商品TBID
     */
    void doOnShelf(Long goodsTbId);

    /**
     * 执行删除操作
     * 若当前用户非管理员，请务必先进行权限验证(isAllowedToOperateGoods)
     *
     * @param goodsTbId 商品TBID
     */
    void doDeleteGoods(Long goodsTbId);
}
