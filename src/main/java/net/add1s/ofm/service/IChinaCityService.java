package net.add1s.ofm.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.business.ChinaCity;

import java.util.List;

public interface IChinaCityService extends IService<ChinaCity> {

    /**
     * 省市区
     *
     * @return city tree
     */
    List<Tree<String>> generateCityTreeDeep2();

    /**
     * full deep
     *
     * @return city tree
     */
    List<Tree<String>> generateCityTreeDeep3();

    ChinaCity getCityInfoById(Long id);
}
