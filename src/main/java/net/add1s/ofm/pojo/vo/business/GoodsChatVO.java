package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.vo.sys.SysUserVO;

import java.io.Serializable;

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
}
