package net.add1s.ofm.controller;

import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.service.ICommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    private final ICommonService commonService;

    public CommonController(ICommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/appInfo")
    public Res appInfo() {
        return Res.ok(commonService.appInfo());
    }
}
