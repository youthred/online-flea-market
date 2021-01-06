package net.add1s.ofm.controller;

import net.add1s.ofm.common.response.Res;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @PostMapping("/search")
    public Res search() {
        return Res.ok();
    }
}
