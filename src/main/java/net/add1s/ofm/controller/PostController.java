package net.add1s.ofm.controller;

import cn.hutool.core.util.StrUtil;
import net.add1s.ofm.common.response.Res;
import org.springframework.web.bind.annotation.*;

/**
 * @author pj.w@qq.com
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @GetMapping("/{postTbId}")
    public Res getOneByPostTbId(@PathVariable("postTbId") Long postTbId) {
        return Res.ok(StrUtil.format("{} - {}", RequestMethod.GET, postTbId));
    }

    @DeleteMapping("/{postTbId}")
    public Res deleteOneByPostTbId(@PathVariable("postTbId") Long postTbId) {
        return Res.ok(StrUtil.format("{} - {}", RequestMethod.DELETE, postTbId));
    }
}
