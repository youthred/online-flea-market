package net.add1s.ofm.pojo.vo.business;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;
import net.add1s.ofm.util.IntervalsUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class GoodsChatVO implements Serializable {

    private static final long serialVersionUID = -7140675880366479127L;

    /**
     * 当前会话窗口是否激活
     */
    private boolean active;
    /**
     * 卖家
     */
    private SysUserVO seller;
    /**
     * 商品
     */
    private GoodsVO goods;
    /**
     * 最后一条消息
     */
    private ChatMessageVO lastMessage;
    /**
     * 间隔时间秒描述，eg：1秒种/2分钟/3小时/4天前/（超一周）2021年1月1日
     */
    private String intervalsDesc;
    /**0
     * 未读消息
     */
    private int unread;

    public String getIntervals() {
        Duration between = LocalDateTimeUtil.between(LocalDateTime.now(), this.lastMessage.getCreateTime());
        String simple = IntervalsUtil.simple(between.abs().toMillis());
        return StringUtils.isBlank(simple) ? LocalDateTimeUtil.format(lastMessage.getCreateTime(), DatePattern.CHINESE_DATE_PATTERN) : simple;
    }
}
