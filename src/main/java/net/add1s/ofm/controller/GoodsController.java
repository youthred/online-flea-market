package net.add1s.ofm.controller;

import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.common.response.Res;
import net.add1s.ofm.pojo.dto.GoodsDTO;
import net.add1s.ofm.pojo.dto.GoodsReportDTO;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final IGoodsService iGoodsService;
    private final ISysUserService iSysUserService;

    public GoodsController(IGoodsService iGoodsService,
                           ISysUserService iSysUserService) {
        this.iGoodsService = iGoodsService;
        this.iSysUserService = iSysUserService;
    }

    /**
     * 分页
     *
     * @param mbpPage 分页及查询参数
     * @return Res
     */
    @PostMapping("/page")
    public Res page(@RequestBody @Validated MbpPage<Goods> mbpPage) {
        return Res.ok(iGoodsService.goodsPage(mbpPage));
    }

    /**
     * 通过TBID跳转对应商品详情
     *
     * @param goodsTbId 商品TBID
     * @return Res
     */
    @GetMapping("/{goodsTbId}")
    public Res detail(@PathVariable("goodsTbId") Long goodsTbId) {
        return Res.ok(iGoodsService.detail(goodsTbId));
    }

    /**
     * 通过TBID跳转对应商品详情页面
     *
     * @param goodsTbId 商品TBID
     * @return ModelAndView
     */
    @GetMapping("/{goodsTbId}.html")
    public ModelAndView detailPage(@PathVariable("goodsTbId") Long goodsTbId) {
        iGoodsService.view(goodsTbId);
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("goodsDetail", iGoodsService.detailOfOnShelfAndUnDeleted(goodsTbId));
        detailMap.put("currentLoginUser", iSysUserService.isLogin() ? iSysUserService.currentUser() : null);
        return new ModelAndView("goods/detail", "detail", detailMap);
    }

    /**
     * 举报
     *
     * @param goodsReportDTO 举报请求体
     * @return Res
     */
    @PostMapping("/report")
    public Res report(@RequestBody @Validated GoodsReportDTO goodsReportDTO) {
        iGoodsService.report(goodsReportDTO);
        return Res.ok();
    }

    /**
     * 私聊页面
     *
     * @param goodsTbId 商品TBID
     * @return ModelAndView
     */
    @GetMapping("/doChat/{goodsTbId}.html")
    public ModelAndView doChat(@PathVariable("goodsTbId") Long goodsTbId) {
        return new ModelAndView("goods/doChat", "chat", iGoodsService.chat(goodsTbId));
    }

    /**
     * 下架商品
     *
     * @param goodsTbId 商品TBID
     * @return Res
     */
    @PutMapping("/offShelf/{goodsTbId}")
    public Res offShelf(@PathVariable("goodsTbId") Long goodsTbId) {
        iGoodsService.offShelf(goodsTbId, true);
        return Res.ok();
    }

    /**
     * 重新发布商品
     *
     * @param goodsTbId 商品TBID
     * @return Res
     */
    @PutMapping("/onShelf/{goodsTbId}")
    public Res onShelf(@PathVariable("goodsTbId") Long goodsTbId) {
        iGoodsService.onShelf(goodsTbId, true);
        return Res.ok();
    }

    /**
     * 新增闲置
     *
     * @param goodsDTO GoodsDTO
     * @return Res
     */
    @PostMapping("/save")
    public Res saveNewGoods(@RequestBody @Validated GoodsDTO goodsDTO) {
        iGoodsService.saveNewGoods(goodsDTO);
        return Res.ok();
    }

    /**
     * 更新商品
     *
     * @param goodsDTO GoodsDTO
     * @return Res
     */
    @PutMapping("/update")
    public Res updateGoods(@RequestBody @Validated GoodsDTO goodsDTO) {
        iGoodsService.updateGoods(goodsDTO);
        return Res.ok();
    }

    /**
     * 删除商品，更新为[下架、已删除]
     *
     * @param goodsTbId 商品TBID
     * @return Res
     */
    @DeleteMapping("/delete/{goodsTbId}")
    public Res deleteGoods(@PathVariable("goodsTbId") Long goodsTbId) {
        iGoodsService.deleteGoods(goodsTbId, true);
        return Res.ok();
    }

    /**
     * 商品购买
     * 避免@Service里事务注解@Transactional与synchronized同步锁关键字冲突失效，所以在表现层加锁
     *
     * @param goodsTbId 商品TBID
     * @return Res
     */
    @PutMapping("/buy/{goodsTbId}")
    public synchronized Res buy(@PathVariable("goodsTbId") Long goodsTbId) {
        iGoodsService.buy(goodsTbId);
        return Res.ok();
    }
}
