package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;

import java.util.List;

public interface IUserHomeMyService {

    // region 我发布的 posted
    IPage<Goods> myPostedPage(MbpPage<Goods> mbpPage);
    // endregion

    // region 我卖出的 sold
    IPage<Goods> mySoldPage(MbpPage<Goods> mbpPage);
    // endregion

    // region 我买到的 bought
    IPage<Goods> myBoughtPage(MbpPage<Goods> mbpPage);
    // endregion

    // region 我的私聊 privateChat
    List<GoodsChatVO> chats();
    // endregion

    // region 我的点赞 like
    // endregion

    // region 我的评论 comments
    // endregion
}
