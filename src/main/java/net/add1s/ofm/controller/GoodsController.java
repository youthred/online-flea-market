package net.add1s.ofm.controller;

import net.add1s.ofm.cache.SimpleCacheManager;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.service.IParameterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final IGoodsService iGoodsService;
    private final IParameterService iParameterService;

    public GoodsController(IGoodsService iGoodsService,
                           IParameterService iParameterService) {
        this.iGoodsService = iGoodsService;
        this.iParameterService = iParameterService;
    }

    /**
     * 搜索所有商品
     *
     * @param q 搜索输入
     * @return Res
     */
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("q") String q) {
        return new ModelAndView("/goods/search", "searchRes", iGoodsService.search(q));
    }

    /**
     * 中国城市四级树
     *
     * @return Res
     */
    @GetMapping("/chinaCityTree")
    public Res chinaCityTree() {
        return Res.ok(SimpleCacheManager.cityTree);
    }

    /**
     * 分页
     *
     * @param mbpPage 分页及查询参数
     * @return Res
     */
    @PostMapping("/page")
    public Res page(@RequestBody @Validated MbpPage<Goods, Goods> mbpPage) {
        return Res.ok(iGoodsService.page(mbpPage.getPage(), mbpPage.toQueryWrapper()));
    }

    /**
     * 商品类型
     *
     * @return Res
     */
    @GetMapping("/goodsTypes")
    public Res goodsTypes() {
        return Res.ok(iParameterService.goodsTypes());
    }

    /**
     * 商品详情
     *
     * @param goodsTbId 商品TBID
     * @return Res
     */
    @GetMapping("/{goodsTbId}.html")
    public ModelAndView detail(@PathVariable("goodsTbId") Long goodsTbId) {
        iGoodsService.view(goodsTbId);
        return new ModelAndView("goods/detail", "goodsDetail", iGoodsService.details(goodsTbId));
    }
}
