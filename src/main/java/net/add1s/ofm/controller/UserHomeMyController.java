package net.add1s.ofm.controller;

import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.service.IUserHomeMyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户主页-我的
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home/my")
public class UserHomeMyController {

    private final IUserHomeMyService iUserHomeMyService;

    public UserHomeMyController(IUserHomeMyService iUserHomeMyService) {
        this.iUserHomeMyService = iUserHomeMyService;
    }

    // region 我发布的 posted
    /**
     * 我发布的 - 分页
     *
     * @return Res
     */
    @PostMapping("/posted/page")
    public Res postedPage(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeMyService.postedPage(mbpPage));
    }

    /**
     * 新增修改时二手书分类
     * 已隐藏“总类”，CODE=000
     *
     * @return Res
     */
    @GetMapping("/posted/usedBookTypes")
    public Res postedUsedBookTypes() {
        return Res.ok(iUserHomeMyService.postedUsedBookTypes());
    }
    // endregion

    // region 我卖出的 sold
    /**
     * 我卖出的 - 分页
     *
     * @return Res
     */
    @PostMapping("/sold/page")
    public Res soldPage(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeMyService.soldPage(mbpPage));
    }
    // endregion

    // region 我买到的 bought
    /**
     * 我买到的 - 分页
     *
     * @return Res
     */
    @PostMapping("/bought/page")
    public Res boughtPage(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeMyService.boughtPage(mbpPage));
    }
    // endregion

    // region 我的私聊 privateChat

    /**
     * 私聊列表
     *
     * @return ModelAndView
     */
    @GetMapping("/privateChat/chats")
    public Res privateChats() {
        return Res.ok(iUserHomeMyService.privateChats());
    }

    // endregion

    // region 我的点赞 like
    // endregion

    // region 我的评论 comments
    // endregion
}
