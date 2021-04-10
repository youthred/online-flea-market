package net.add1s.ofm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户主页 ModelAndView 页面跳转
 *
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/home")
public class UserHomeMvPageController {

    @GetMapping("/my.html")
    public ModelAndView myPage() {
        return new ModelAndView("home/my");
    }

    @GetMapping("/admin.html")
    public ModelAndView adminPage() {
        return new ModelAndView("home/admin");
    }
}
