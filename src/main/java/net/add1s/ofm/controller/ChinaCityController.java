package net.add1s.ofm.controller;

import net.add1s.ofm.cache.SimpleCacheManager;
import net.add1s.ofm.common.response.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/city")
public class ChinaCityController {

    /**
     * 中国城市四级树
     *
     * @return Res
     */
    @GetMapping("/tree")
    public Res chinaCityTree() {
        return Res.ok(SimpleCacheManager.cityTree);
    }
}
