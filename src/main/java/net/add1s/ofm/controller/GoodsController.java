package net.add1s.ofm.controller;

import net.add1s.ofm.cache.SimpleCacheManager;
import net.add1s.ofm.common.response.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping("/search")
    public Res search(@RequestParam("q") String q) {
        return Res.ok(q);
    }

    @GetMapping("/chinaCityTree")
    public Res chinaCityTree() {
        return Res.ok(SimpleCacheManager.cityTree);
    }
}
