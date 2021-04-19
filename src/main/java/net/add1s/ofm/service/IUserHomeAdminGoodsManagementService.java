package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.GoodsReport;

public interface IUserHomeAdminGoodsManagementService {

    // region 举报审核
    IPage<GoodsReport> reportPage(MbpPage<GoodsReport> mbpPage);
    // endregion
}
