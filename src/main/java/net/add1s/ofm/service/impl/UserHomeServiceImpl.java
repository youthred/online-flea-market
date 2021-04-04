package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.vo.business.GoodsChatVO;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.service.IUserHomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserHomeServiceImpl implements IUserHomeService {

    private final ISysUserService iSysUserService;
    private final IGoodsService iGoodsService;

    public UserHomeServiceImpl(ISysUserService iSysUserService,
                               IGoodsService iGoodsService) {
        this.iSysUserService = iSysUserService;
        this.iGoodsService = iGoodsService;
    }

    // region 我发布的 posted
    @Override
    public IPage<Goods> myPostedPage(MbpPage<Goods> mbpPage) {
        Page<Goods> page = iGoodsService.page(mbpPage.getPage(), mbpPage.toQueryWrapper().lambda().eq(Goods::getSellerSysUserTbId, iSysUserService.currentUser().getTbId()));
        page.convert(GoodsVO::new);
        return page;
    }
    // endregion

    // region 我卖出的 sold
    // endregion

    // region 我买到的 got
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
