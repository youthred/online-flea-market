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
    public Res myPostedPage(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeMyService.myPostedPage(mbpPage));
    }
    // endregion

    // region 我卖出的 sold
    /**
     * 我卖出的 - 分页
     *
     * @return Res
     */
    @PostMapping("/sold/page")
    public Res mySoldPage(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeMyService.mySoldPage(mbpPage));
    }
    // endregion

    // region 我买到的 bought
    /**
     * 我买到的 - 分页
     *
     * @return Res
     */
    @PostMapping("/bought/page")
    public Res myBoughtPage(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeMyService.myBoughtPage(mbpPage));
    }
    // endregion

    // region 我的私聊 privateChat

    /**
     * 私聊列表
     *
     * @return ModelAndView
     */
    @GetMapping("/privateChat/chats")
    public Res chat() {
        return Res.ok(iUserHomeMyService.chats());
    }

    // endregion

    // region 我的点赞 like
    // endregion

    // region 我的评论 comments
    // endregion
}
