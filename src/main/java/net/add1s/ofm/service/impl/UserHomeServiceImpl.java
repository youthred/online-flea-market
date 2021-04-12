package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.entity.business.GoodsOrder;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.pojo.vo.business.BoughtVO;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import net.add1s.ofm.pojo.vo.business.SoldVO;
import net.add1s.ofm.service.IGoodsOrderService;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.service.IUserHomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserHomeServiceImpl implements IUserHomeService {

    private final ISysUserService iSysUserService;
    private final IGoodsService iGoodsService;
    private final IGoodsOrderService iGoodsOrderService;

    public UserHomeServiceImpl(ISysUserService iSysUserService,
                               IGoodsService iGoodsService,
                               IGoodsOrderService iGoodsOrderService) {
        this.iSysUserService = iSysUserService;
        this.iGoodsService = iGoodsService;
        this.iGoodsOrderService = iGoodsOrderService;
    }

    // region 我发布的 posted
    @Override
    public IPage<Goods> myPostedPage(MbpPage<Goods> mbpPage) {
        Page<Goods> page = iGoodsService.page(mbpPage.getPage(), mbpPage.toQueryWrapper().lambda().eq(Goods::getSellerSysUserTbId, iSysUserService.currentUser().getTbId()).eq(Goods::getDeleted, false));
        // 已完成的订单商品TBID
        List<Long> doneOrderGoodsTbIds = iGoodsOrderService.list(Wrappers.lambdaQuery(GoodsOrder.class).select(GoodsOrder::getGoodsTbId).eq(GoodsOrder::getDone, true)).stream().map(GoodsOrder::getGoodsTbId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(doneOrderGoodsTbIds)) {
            // 排除已卖出的
            page.getRecords().removeIf(goods -> doneOrderGoodsTbIds.contains(goods.getTbId()));
        }
        page.convert(GoodsVO::new);
        return page;
    }
    // endregion

    // region 我卖出的 sold
    @Override
    public IPage<Goods> mySoldPage(MbpPage<Goods> mbpPage) {
        Page<Goods> page = new Page<>();
        MyUserDetails currentUser = iSysUserService.currentUser();
        List<GoodsOrder> doneOrderSoled = iGoodsOrderService.list(Wrappers.lambdaQuery(GoodsOrder.class).eq(GoodsOrder::getSellerSysUserTbId, currentUser.getTbId()).eq(GoodsOrder::getDone, true));
        if (!CollectionUtils.isEmpty(doneOrderSoled)) {
            page = iGoodsService.page(mbpPage.getPage(), Wrappers.lambdaQuery(Goods.class).in(Goods::getTbId, doneOrderSoled.stream().map(GoodsOrder::getGoodsTbId).collect(Collectors.toList())));
            page.convert(goods -> new SoldVO(goods, iSysUserService.getById(doneOrderSoled.stream().filter(order -> goods.getTbId().equals(order.getGoodsTbId())).findFirst().get().getBuyerSysUserTbId())));
        }
        return page;
    }
    // endregion

    // region 我买到的 bought
    @Override
    public IPage<Goods> myBoughtPage(MbpPage<Goods> mbpPage) {
        Page<Goods> page = new Page<>();
        MyUserDetails currentUser = iSysUserService.currentUser();
        List<GoodsOrder> doneOrderBought = iGoodsOrderService.list(Wrappers.lambdaQuery(GoodsOrder.class).eq(GoodsOrder::getBuyerSysUserTbId, currentUser.getTbId()).eq(GoodsOrder::getDone, true));
        if (!CollectionUtils.isEmpty(doneOrderBought)) {
            page = iGoodsService.page(mbpPage.getPage(), Wrappers.lambdaQuery(Goods.class).in(Goods::getTbId, doneOrderBought.stream().map(GoodsOrder::getGoodsTbId).collect(Collectors.toList())));
            page.convert(goods -> new BoughtVO(goods, iSysUserService.getById(doneOrderBought.stream().filter(order -> goods.getTbId().equals(order.getGoodsTbId())).findFirst().get().getSellerSysUserTbId())));
        }
        return page;
    }
    // endregion

    // region 我的私聊 privateChat
    @Override
    public List<GoodsChatVO> chats() {
        return iGoodsService.chats();
    }
    // endregion

    // region 我的点赞 like
    // endregion

    // region 我的评论 comments
    // endregion
}
