package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.service.IUserHomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public IPage<Goods> myPosted(MbpPage<Goods> mbpPage) {
        return iGoodsService.page(mbpPage.getPage(), mbpPage.toQueryWrapper().lambda().eq(Goods::getSellerSysUserTbId, iSysUserService.currentUser().getTbId()));
    }
}
