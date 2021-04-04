package net.add1s.ofm.controller;

import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.service.IUserHomeService;
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

    private final IUserHomeService iUserHomeService;

    public UserHomeMyController(IUserHomeService iUserHomeService) {
        this.iUserHomeService = iUserHomeService;
    }

    // region 我发布的 posted

    /**
     * 我发布的
     *
     * @return Res
     */
    @PostMapping("/posted/page")
    public Res myPostedPage(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeService.myPostedPage(mbpPage));
    }

    // endregion

    // region 我卖出的 sold
    // endregion

    // region 我买到的 got
    // endregion

    // region 我的私聊 privateChat

    /**
     * 私聊列表
     *
     * @return ModelAndView
     */
    @GetMapping("/privateChat/chats")
    public Res chat() {
        return Res.ok(iUserHomeService.chats());
    }

    // endregion

    // region 我的点赞 like
    // endregion

    // region 我的评论 comments
    // endregion
}
