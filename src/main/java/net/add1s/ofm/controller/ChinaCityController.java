package net.add1s.ofm.controller;

import net.add1s.ofm.cache.SimpleCacheManager;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.service.IChinaCityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/city")
public class ChinaCityController {

    private final IChinaCityService iChinaCityService;

    public ChinaCityController(IChinaCityService iChinaCityService) {
        this.iChinaCityService = iChinaCityService;
    }

    /**
     * 中国城市省市
     *
     * @return Res
     */
    @GetMapping("/treeDeep1")
    public Res chinaCityTreeDeep1() {
        return Res.ok(SimpleCacheManager.cityTreeDeep1);
    }

    /**
     * 中国城市省市区
     *
     * @return Res
     */
    @GetMapping("/treeDeep2")
    public Res chinaCityTreeDeep2() {
        return Res.ok(SimpleCacheManager.cityTreeDeep2);
    }

    /**
     * 中国城市四级树
     *
     * @return Res
     */
    @GetMapping("/treeDeep3")
    public Res chinaCityTreeDeep3() {
        return Res.ok(SimpleCacheManager.cityTreeDeep3);
    }

    /**
     * 通过ID获取（不是TBID）
     *
     * @param id city id
     * @return Res
     */
    @GetMapping("/{id}")
    public Res getCityInfoById(@PathVariable("id") Long id) {
        return Res.ok(iChinaCityService.getCityInfoById(id));
    }
}
