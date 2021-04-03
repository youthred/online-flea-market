package net.add1s.ofm.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.mapper.ChinaCityMapper;
import net.add1s.ofm.pojo.entity.business.ChinaCity;
import net.add1s.ofm.service.IChinaCityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ChinaCityServiceImpl extends ServiceImpl<ChinaCityMapper, ChinaCity> implements IChinaCityService {

    @Override
    public List<Tree<String>> generateCityTreeDeep2() {
        List<ChinaCity> chinaCities = CollUtil.newArrayList(this.list(Wrappers.lambdaQuery(ChinaCity.class).lt(ChinaCity::getDeep, 3)));
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setParentIdKey("pid");
        treeNodeConfig.setDeep(3);
        return TreeUtil.build(
                chinaCities,
                "0",
                treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getPid().toString());
                    tree.setName(treeNode.getName());
                    tree.putExtra("deep", treeNode.getDeep());
                    tree.putExtra("pinyinPrefix", treeNode.getPinyinPrefix());
                    tree.putExtra("pinyin", treeNode.getPinyin());
                    tree.putExtra("extId", treeNode.getExtId());
                    tree.putExtra("extName", treeNode.getExtName());
                    // for vue tree
                    tree.putExtra("radioDisabled", treeNode.getDeep() != 2);   // 仅最后一级可选
                }
        );
    }

    @Override
    public List<Tree<String>> generateCityTreeDeep3() {
        List<ChinaCity> chinaCities = CollUtil.newArrayList(this.list());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setParentIdKey("pid");
        treeNodeConfig.setDeep(4);
        return TreeUtil.build(
                chinaCities,
                "0",
                treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getPid().toString());
                    tree.setName(treeNode.getName());
                    tree.putExtra("deep", treeNode.getDeep());
                    tree.putExtra("pinyinPrefix", treeNode.getPinyinPrefix());
                    tree.putExtra("pinyin", treeNode.getPinyin());
                    tree.putExtra("extId", treeNode.getExtId());
                    tree.putExtra("extName", treeNode.getExtName());
                }
        );
    }
}
