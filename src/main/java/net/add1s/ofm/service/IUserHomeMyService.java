package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.ParameterVO;
import net.add1s.ofm.pojo.vo.business.SoldOrBoughtVO;

import java.util.List;

public interface IUserHomeMyService {

    // region 我发布的 posted
    IPage<Goods> postedPage(MbpPage<Goods> mbpPage);
    List<ParameterVO> postedUsedBookTypes();
    // endregion

    // region 我卖出的 sold
    IPage<SoldOrBoughtVO> soldPage(MbpPage<Goods> mbpPage);
    // endregion

    // region 我买到的 bought
    IPage<SoldOrBoughtVO> boughtPage(MbpPage<Goods> mbpPage);
    // endregion

    // region 我的私聊 privateChat
    List<GoodsChatVO> privateChats();
    // endregion

    // region 我的点赞 like
    // endregion

    // region 我的评论 comments
    // endregion
}
