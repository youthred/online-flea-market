package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.common.enums.QueryTypeEnum;
import net.add1s.ofm.common.enums.Symbol;
import net.add1s.ofm.common.enums.TransactionRoleEnum;
import net.add1s.ofm.common.exception.BusinessException;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.mapper.GoodsMapper;
import net.add1s.ofm.pojo.dto.GoodsDTO;
import net.add1s.ofm.pojo.dto.GoodsReportDTO;
import net.add1s.ofm.pojo.entity.business.ChatMessage;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.entity.business.GoodsOrder;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.pojo.vo.business.ChatMessageVO;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import net.add1s.ofm.service.*;
import net.add1s.ofm.util.SqlUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    private final IGoodsReportService iGoodsReportService;
    private final ISysUserService iSysUserService;
    private final IChatMessageService iChatMessageService;
    private final IGoodsOrderService iGoodsOrderService;

    public GoodsServiceImpl(IGoodsReportService iGoodsReportService,
                            ISysUserService iSysUserService,
                            IChatMessageService iChatMessageService,
                            IGoodsOrderService iGoodsOrderService) {
        this.iGoodsReportService = iGoodsReportService;
        this.iSysUserService = iSysUserService;
        this.iChatMessageService = iChatMessageService;
        this.iGoodsOrderService = iGoodsOrderService;
    }

    @Override
    public GoodsVO detailOfOnShelfAndUnDeleted(Long goodsTbId) {
        return this.baseMapper.findOnShelfAndUnDeletedGoodsDetailByTbId(goodsTbId);
    }

    @Override
    public GoodsVO detail(Long goodsTbId) {
        return this.baseMapper.findGoodsDetailByTbId(goodsTbId);
    }

    @Override
    public IPage<Goods> goodsPage(MbpPage<Goods> mbpPage) {
        boolean remove = mbpPage.getQueryOptions().removeIf(queryOption -> "desc".equals(queryOption.getKey()) && Symbol.EMPTY_STRING.getValue().equals(queryOption.getValue().toString()));
        if (!remove) {
            mbpPage.getQueryOptions().forEach(queryOption -> {
                if ("desc".equals(queryOption.getKey())) {
                    queryOption.setValue(SqlUtil.spaceToPresent(queryOption.getValue().toString()));
                    queryOption.setType(QueryTypeEnum.like);
                }
            });
        }
        Page<Goods> page = this.page(mbpPage.getPage(), mbpPage.toQueryWrapper().lambda().eq(Goods::getOffShelf, false).eq(Goods::getDeleted, false));
        page.convert(GoodsVO::new);
        return page;
    }

    @Override
    public void view(Long goodsTbId) {
        this.baseMapper.viewsAdd(goodsTbId);
    }

    @Override
    public void report(GoodsReportDTO goodsReportDTO) {
        iGoodsReportService.save(
                new GoodsReport()
                        .setCreateTime(LocalDateTime.now())
                        .setGoodsTbId(goodsReportDTO.getGoodsTbId())
                        .setReason(goodsReportDTO.getReason())
                        .setReviewed(false)
        );
    }

    @Override
    public List<GoodsChatVO> chats() {
        MyUserDetails currentUser = iSysUserService.currentUser();
        List<GoodsChatVO> chatList = this.baseMapper.findChatList(currentUser.getTbId());
        chatList.forEach(goodsChatVO ->
                goodsChatVO
                        .setChatMessages(
                                iChatMessageService.list(
                                        Wrappers.lambdaQuery(ChatMessage.class)
                                                .and(chatMessageLambdaQueryWrapper -> chatMessageLambdaQueryWrapper
                                                        .eq(ChatMessage::getBuyerSysUserTbId, currentUser.getTbId())
                                                        .or().eq(ChatMessage::getSellerSysUserTbId, currentUser.getTbId()))
                                                .eq(ChatMessage::getGoodsTbId, goodsChatVO.getGoods().getTbId())
                                ).stream().map(ChatMessageVO::new).collect(Collectors.toList()))
                        .setCurrentTransactionRole(currentUser.getTbId())
                        .setUnread()
                        .setChatMessages(null)  // 清空历史聊天信息，减小传输压力
        );
        return chatList;
    }

    @Override
    public GoodsChatVO chat(Long goodsTbId) {
        MyUserDetails currentUser = iSysUserService.currentUser();
        LambdaQueryWrapper<ChatMessage> historyChatLambdaWrapper = Wrappers.lambdaQuery(ChatMessage.class)
                .eq(ChatMessage::getGoodsTbId, goodsTbId)
                .and(chatMessageLambdaQueryWrapper -> chatMessageLambdaQueryWrapper
                        .eq(ChatMessage::getBuyerSysUserTbId, currentUser.getTbId())
                        .or().eq(ChatMessage::getSellerSysUserTbId, currentUser.getTbId()));
        // 查询当前用户当前商品的历史私聊记录
        int historyChatCount = iChatMessageService.count(historyChatLambdaWrapper);
        if (historyChatCount == 0) {
            firstChat(currentUser.getTbId(), goodsTbId);
        }
        List<ChatMessage> historyChatMessages = iChatMessageService.list(historyChatLambdaWrapper);
        List<ChatMessageVO> chatMessageVOS = historyChatMessages
                .stream()
                .map(ChatMessageVO::new)
                .collect(Collectors.toList());
        chatMessageVOS.forEach(chatMessageVO -> chatMessageVO.setIsFromCurrentUser(currentUser.getTbId()));
        GoodsOrder sold = iGoodsOrderService.getOne(Wrappers.lambdaQuery(GoodsOrder.class).eq(GoodsOrder::getGoodsTbId, goodsTbId));
        boolean isSold = Objects.nonNull(sold);
        GoodsChatVO goodsChatVO = new GoodsChatVO()
                .setGoods(this.detail(goodsTbId))   // 不管是否下架或删除
                .setChatMessages(chatMessageVOS)
                .setCurrentTransactionRole(currentUser.getTbId())
                .setSold(isSold)
                .setBoughtByCurrentUser(isSold && sold.getBuyerSysUserTbId().equals(currentUser.getTbId()));
        // 未读消息变已读
        if (TransactionRoleEnum.SELLER.equals(goodsChatVO.getCurrentTransactionRole())) {
            // 当前用户为卖家，则修改当前商品的[卖家已读]为true
            iChatMessageService.update(Wrappers.lambdaUpdate(ChatMessage.class)
                    .eq(ChatMessage::getGoodsTbId, goodsTbId)
                    .eq(ChatMessage::getSellerSysUserTbId, currentUser.getTbId())
                    .set(ChatMessage::isReadSeller, true)
            );
        } else {
            // 当前用户为买家
            iChatMessageService.update(Wrappers.lambdaUpdate(ChatMessage.class)
                    .eq(ChatMessage::getGoodsTbId, goodsTbId)
                    .eq(ChatMessage::getBuyerSysUserTbId, currentUser.getTbId())
                    .set(ChatMessage::isReadBuyer, true)
            );
        }
        return goodsChatVO;
    }

    @Override
    public void offShelf(Long goodsTbId, boolean checkAuth) {
        if (checkAuth) {
            if (isAllowedToOperateGoods(goodsTbId)) {
                this.update(Wrappers.lambdaUpdate(Goods.class).set(Goods::getOffShelf, true).set(Goods::getUpdateTime, LocalDateTime.now()).eq(Goods::getTbId, goodsTbId));
            } else {
                throw new BusinessException("权限不足，禁止操作其他用户的商品");
            }
        } else {
            this.update(Wrappers.lambdaUpdate(Goods.class).set(Goods::getOffShelf, true).set(Goods::getUpdateTime, LocalDateTime.now()).eq(Goods::getTbId, goodsTbId));
        }
    }

    @Override
    public void onShelf(Long goodsTbId, boolean checkAuth) {
        if (checkAuth) {
            if (isAllowedToOperateGoods(goodsTbId)) {
                this.update(Wrappers.lambdaUpdate(Goods.class).set(Goods::getOffShelf, false).set(Goods::getUpdateTime, LocalDateTime.now()).eq(Goods::getTbId, goodsTbId));
            } else {
                throw new BusinessException("权限不足，禁止操作其他用户的商品");
            }
        } else {
            this.update(Wrappers.lambdaUpdate(Goods.class).set(Goods::getOffShelf, false).set(Goods::getUpdateTime, LocalDateTime.now()).eq(Goods::getTbId, goodsTbId));
        }
    }

    @Override
    public void saveNewGoods(GoodsDTO goodsDTO) {
        goodsDTO.setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setSellerSysUserTbId(iSysUserService.currentUser().getTbId())
                .setOffShelf(false)
                .setDeleted(false);
        this.save(goodsDTO.toEntity());
    }

    @Override
    public void updateGoods(GoodsDTO goodsDTO) {
        if (isAllowedToOperateGoods(goodsDTO.getTbId())) {
            Goods goods = goodsDTO.setUpdateTime(LocalDateTime.now()).toEntity();
            this.update(
                    Wrappers.lambdaUpdate(Goods.class)
                            .eq(Goods::getTbId, goods.getTbId())
                            .set(Goods::getDesc, goods.getDesc())
                            .set(Goods::getPics, goods.getPics())
                            .set(Goods::getPrice, goods.getPrice())
                            .set(Goods::getCityId, goods.getCityId())
                            .set(Goods::getUpdateTime, goods.getUpdateTime())
            );
        } else {
            throw new BusinessException("权限不足，禁止操作其他用户的商品");
        }
    }

    @Override
    public void deleteGoods(Long goodsTbId) {
        if (isAllowedToOperateGoods(goodsTbId)) {
            this.update(Wrappers.lambdaUpdate(Goods.class).set(Goods::getOffShelf, true).set(Goods::getDeleted, true).eq(Goods::getTbId, goodsTbId));
        } else {
            throw new BusinessException("权限不足，禁止操作其他用户的商品");
        }
    }

    @Override
    public void buy(Long goodsTbId) {
        Goods targetGoods = this.getById(goodsTbId);
        if (Objects.nonNull(targetGoods)) {
            if (iGoodsOrderService.count(Wrappers.lambdaQuery(GoodsOrder.class).eq(GoodsOrder::getGoodsTbId, targetGoods.getTbId())) == 0) {
                MyUserDetails currentUser = iSysUserService.currentUser();
                if (currentUser.getTbId().equals(targetGoods.getSellerSysUserTbId())) {
                    throw new BusinessException("当前用户为该商品卖家，无法购买");
                }
                GoodsOrder goodsOrder = new GoodsOrder()
                        .setCreateTime(LocalDateTime.now())
                        .setGoodsTbId(goodsTbId)
                        .setOrdered(true)
                        .setPaid(true)
                        .setSellerSysUserTbId(targetGoods.getSellerSysUserTbId())
                        .setBuyerSysUserTbId(currentUser.getTbId())
                        .setCompleteTime(LocalDateTime.now())
                        .setDone(true);
                iGoodsOrderService.save(goodsOrder);
                this.offShelf(goodsTbId, false);
            } else {
                throw new BusinessException("商品已被购买");
            }
        } else {
            throw new BusinessException("商品不存在");
        }
    }

    /**
     * 第一次私聊发送默认消息
     *
     * @param buyerSysUserTbId 卖家用户TBID
     * @param goodsTbId        商品TBID
     */
    private void firstChat(Long buyerSysUserTbId, Long goodsTbId) {
        ChatMessage firstChatOfThis = iChatMessageService.getOne(Wrappers.lambdaQuery(ChatMessage.class).eq(ChatMessage::getBuyerSysUserTbId, buyerSysUserTbId).eq(ChatMessage::getGoodsTbId, goodsTbId).eq(ChatMessage::getMessageTypeCode, -1));
        if (Objects.isNull(firstChatOfThis)) {
            iChatMessageService.save(
                    new ChatMessage()
                            .setCreateTime(LocalDateTime.now())
                            .setGoodsTbId(goodsTbId)
                            .setBuyerSysUserTbId(buyerSysUserTbId)
                            .setSellerSysUserTbId(this.getById(goodsTbId).getSellerSysUserTbId())
                            .setMessageContent("你好")
                            .setMessageTypeCode((short) -1)
                            .setSenderSysUserTbId(buyerSysUserTbId)
                            .setReadBuyer(true)
            );
        }
    }

    /**
     * 当前用户是否允许操作某一商品，仅未卖商品时的超级管理员或商品卖家允许操作
     *
     * @param goodsTbId 商品TBID
     * @return 是否允许
     */
    private boolean isAllowedToOperateGoods(Long goodsTbId) {
        MyUserDetails currentUser = iSysUserService.currentUser();
        // 未出售 且 ( 超级管理员 或 商品卖家 ) 才允许操作
        return iGoodsOrderService.count(Wrappers.lambdaQuery(GoodsOrder.class).eq(GoodsOrder::getGoodsTbId, goodsTbId)) == 0
                && (currentUser.isAdmin() || this.getById(goodsTbId).getSellerSysUserTbId().equals(currentUser.getTbId()));
    }
}
