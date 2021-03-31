package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.Goods;

public interface IUserHomeService {

    IPage<Goods> myPostedPage(MbpPage<Goods> mbpPage);

    void offShelf(Long goodsTbId);

    void onShelf(Long goodsTbId);
}
