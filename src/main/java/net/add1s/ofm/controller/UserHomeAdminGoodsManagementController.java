package net.add1s.ofm.controller;

import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.service.IUserHomeAdminGoodsManagementService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home/admin/goodsManagement")
public class UserHomeAdminGoodsManagementController {

    private final IUserHomeAdminGoodsManagementService iUserHomeAdminGoodsManagementService;

    public UserHomeAdminGoodsManagementController(IUserHomeAdminGoodsManagementService iUserHomeAdminGoodsManagementService) {
        this.iUserHomeAdminGoodsManagementService = iUserHomeAdminGoodsManagementService;
    }

    // region 举报审核
    @PostMapping("/reportPage")
    public Res reportPage(@RequestBody @Validated MbpPage<GoodsReport> mbpPage) {
        return Res.ok(iUserHomeAdminGoodsManagementService.reportPage(mbpPage));
    }
    // endregion
}
