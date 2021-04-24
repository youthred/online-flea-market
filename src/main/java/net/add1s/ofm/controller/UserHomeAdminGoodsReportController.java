package net.add1s.ofm.controller;

import lombok.AllArgsConstructor;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.service.IUserHomeAdminGoodsReportService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统管理 - 商品管理 - 举报审核
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home/admin/user/user")
@AllArgsConstructor
public class UserHomeAdminGoodsReportController {

    private final IUserHomeAdminGoodsReportService iUserHomeAdminGoodsReportService;

    @PostMapping("/page")
    public Res page(@RequestBody @Validated MbpPage<GoodsReport> mbpPage) {
        return Res.ok(iUserHomeAdminGoodsReportService.page(mbpPage));
    }

    /**
     * 审核
     *
     * @param reportTbId 举报审核TBID
     * @param pass 举报是否通过 (1|0 or true|false)
     * @return Res
     */
    @PutMapping("/review/{reportTbId}/{pass}")
    public synchronized Res review(@PathVariable("reportTbId") Long reportTbId, @PathVariable("pass") boolean pass) {
        iUserHomeAdminGoodsReportService.review(reportTbId, pass);
        return Res.ok();
    }
}
