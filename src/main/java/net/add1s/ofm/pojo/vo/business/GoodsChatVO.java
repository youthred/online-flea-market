package net.add1s.ofm.pojo.vo.business;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.common.enums.TransactionRoleEnum;
import net.add1s.ofm.util.IntervalsUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class GoodsChatVO implements Serializable {

    private static final long serialVersionUID = -7140675880366479127L;
    /**
     * 商品
     */
    private GoodsVO goods;
    /**
     * 最后一条消息
     */
    private ChatMessageVO lastMessage;
    /**
     * 所有聊天记录
     */
    private List<ChatMessageVO> chatMessages;
    /**
     * 间隔时间秒描述，eg：1秒种/2分钟/3小时/4天前/（超一周）2021年1月1日
     */
    private String intervalsDesc;
    /**
     * 未读消息
     */
    private long unread;

    /**
     * 当前用户交易角色
     */
    private TransactionRoleEnum currentTransactionRole;

    public String getIntervals() {
        if (Objects.nonNull(this.lastMessage)) {
            Duration between = LocalDateTimeUtil.between(LocalDateTime.now(), this.lastMessage.getCreateTime());
            String simple = IntervalsUtil.simple(between.abs().toMillis());
            return StringUtils.isBlank(simple) ? LocalDateTimeUtil.format(lastMessage.getCreateTime(), DatePattern.CHINESE_DATE_PATTERN) : simple;
        }
        return null;
    }

    public GoodsChatVO setCurrentTransactionRole(Long currentLoginSysUserTbId) {
        if (Objects.nonNull(this.goods.getSeller())) {
            this.currentTransactionRole = currentLoginSysUserTbId.equals(this.goods.getSeller().getTbId()) ? TransactionRoleEnum.SELLER : TransactionRoleEnum.BUYER;
        }
        return this;
    }

    public GoodsChatVO setUnread() {
        if (Objects.nonNull(this.currentTransactionRole)) {
            if (this.currentTransactionRole.equals(TransactionRoleEnum.SELLER)) {
                this.unread = this.chatMessages.stream().filter(chatMessageVO -> !chatMessageVO.isReadSeller()).count();
            } else {
                this.unread = this.chatMessages.stream().filter(chatMessageVO -> !chatMessageVO.isReadBuyer()).count();
            }
        }
        return this;
    }
}
