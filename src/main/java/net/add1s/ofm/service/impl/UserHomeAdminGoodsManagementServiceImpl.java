package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.service.IGoodsReportService;
import net.add1s.ofm.service.IUserHomeAdminGoodsManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserHomeAdminGoodsManagementServiceImpl implements IUserHomeAdminGoodsManagementService {

    private final IGoodsReportService iGoodsReportService;

    public UserHomeAdminGoodsManagementServiceImpl(IGoodsReportService iGoodsReportService) {
        this.iGoodsReportService = iGoodsReportService;
    }

    // region 举报审核
    @Override
    public IPage<GoodsReport> reportPage(MbpPage<GoodsReport> mbpPage) {
        return iGoodsReportService.page(mbpPage.getPage(), mbpPage.toQueryWrapper());
    }
    // endregion
}
