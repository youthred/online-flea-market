package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.common.enums.Symbol;
import net.add1s.ofm.mapper.GoodsMapper;
import net.add1s.ofm.pojo.dto.GoodsReportDTO;
import net.add1s.ofm.pojo.entity.business.ChatMessage;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.pojo.vo.business.ChatMessageVO;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import net.add1s.ofm.service.IChatMessageService;
import net.add1s.ofm.service.IGoodsReportService;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.util.SqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    private final IGoodsReportService iGoodsReportService;
    private final ISysUserService iSysUserService;
    private final IChatMessageService iChatMessageService;

    public GoodsServiceImpl(IGoodsReportService iGoodsReportService,
                            ISysUserService iSysUserService,
                            IChatMessageService iChatMessageService) {
        this.iGoodsReportService = iGoodsReportService;
        this.iSysUserService = iSysUserService;
        this.iChatMessageService = iChatMessageService;
    }

    @Override
    public GoodsVO detail(Long goodsTbId) {
        return this.baseMapper.findGoodsDetail(goodsTbId);
    }

    @Override
    public List<GoodsVO> search(String q) {
        // " +": 匹配所有连续空格，replace为一个空格
        String[] searches = SqlUtil.escapeLike(q.trim()).replaceAll(" +", " ").split(Symbol.SPACE.getValue());
        List<String> searchesTrimmed = Arrays.stream(searches).distinct().map(String::trim).collect(Collectors.toList());
        String like = StringUtils.join(searchesTrimmed, Symbol.PERCENT.getValue());
        return this.baseMapper.findByDesc(like);
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
        List<ChatMessage> existChatMessages = iChatMessageService.list(
                Wrappers.lambdaQuery(ChatMessage.class)
                        .eq(ChatMessage::getGoodsTbId, goodsTbId)
                        .and(chatMessageLambdaQueryWrapper -> chatMessageLambdaQueryWrapper
                                .eq(ChatMessage::getBuyerSysUserTbId, currentUser.getTbId())
                                .or().eq(ChatMessage::getSellerSysUserTbId, currentUser.getTbId()))
        );
        if (CollectionUtils.isEmpty(existChatMessages)) {
            firstChat(currentUser.getTbId(), goodsTbId);
        }
        List<ChatMessageVO> chatMessageVOS = existChatMessages
                .stream()
                .map(ChatMessageVO::new)
                .collect(Collectors.toList());
        chatMessageVOS.forEach(chatMessageVO -> chatMessageVO.setIsFromCurrentUser(currentUser.getTbId()));
        return new GoodsChatVO()
                .setGoods(this.detail(goodsTbId))
                .setChatMessages(chatMessageVOS)
                .setCurrentTransactionRole(currentUser.getTbId());
    }

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
            );
        }
    }
}
