package net.add1s.ofm.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.business.ChinaCityLevel4;

import java.util.List;

public interface IChinaCityLevel4Service extends IService<ChinaCityLevel4> {

    List<Tree<String>> generateCityTree();
}
