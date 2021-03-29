package net.add1s.ofm.controller;

import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.service.IUserHomeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户主页
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home")
public class UserHomeController {

    private final IUserHomeService iUserHomeService;

    public UserHomeController(IUserHomeService iUserHomeService) {
        this.iUserHomeService = iUserHomeService;
    }

    // region 我的

    @GetMapping("/my.html")
    public ModelAndView myPage() {
        return new ModelAndView("home/my");
    }

    /**
     * 我发布的
     *
     * @return Res
     */
    @PostMapping("/my/posted")
    public Res myPosted(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iUserHomeService.myPosted(mbpPage));
    }

    // endregion
}
