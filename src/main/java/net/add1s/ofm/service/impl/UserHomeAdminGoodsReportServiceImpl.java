package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;
import net.add1s.ofm.service.IGoodsReportService;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.service.ISysUserService;
import net.add1s.ofm.service.IUserHomeAdminGoodsReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UserHomeAdminGoodsReportServiceImpl implements IUserHomeAdminGoodsReportService {

    private final IGoodsReportService iGoodsReportService;
    private final ISysUserService iSysUserService;
    private final IGoodsService iGoodsService;

    @Override
    public IPage<GoodsReport> page(MbpPage<GoodsReport> mbpPage) {
        LambdaQueryWrapper<GoodsReport> lambdaQueryWrapper = mbpPage.toQueryWrapper().lambda();
        if (mbpPage.getQueryOptions().stream().anyMatch(queryOption -> "reviewed".equals(queryOption.getKey()) && "1".equals(queryOption.getValue()))) {
            lambdaQueryWrapper.eq(GoodsReport::getReviewerSysUserTbId, iSysUserService.currentUser().getTbId());
        }
        return iGoodsReportService.page(mbpPage.getPage(), lambdaQueryWrapper);
    }

    @Override
    public void review(Long reportTbId, boolean pass) {
        MyUserDetails currentUser = iSysUserService.currentUser();
        iGoodsReportService.update(Wrappers.lambdaUpdate(GoodsReport.class)
                .eq(GoodsReport::getTbId, reportTbId)
                .set(GoodsReport::getReviewed, true)
                .set(GoodsReport::getPassed, pass)
                .set(GoodsReport::getReviewerSysUserTbId, currentUser.getTbId())
                .set(GoodsReport::getReviewTime, LocalDateTime.now())
        );
        if (pass) {
            Long targetGoodsTbId = iGoodsReportService.getById(reportTbId).getGoodsTbId();
            // 下架目标商品
            iGoodsService.doOffShelf(targetGoodsTbId);
            // 清理目标商品的其余举报数据
            iGoodsReportService.remove(Wrappers.lambdaQuery(GoodsReport.class).eq(GoodsReport::getGoodsTbId, targetGoodsTbId).ne(GoodsReport::getTbId, reportTbId));
        }
    }
}
