package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.GoodsReport;

public interface IUserHomeAdminGoodsManagementReportService {

    IPage<GoodsReport> reportPage(MbpPage<GoodsReport> mbpPage);

    void review(Long reportTbId, boolean pass);
}
