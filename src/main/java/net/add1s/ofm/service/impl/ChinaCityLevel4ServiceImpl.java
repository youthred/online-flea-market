package net.add1s.ofm.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.mapper.ChinaCityLevel4Mapper;
import net.add1s.ofm.pojo.entity.business.ChinaCityLevel4;
import net.add1s.ofm.service.IChinaCityLevel4Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ChinaCityLevel4ServiceImpl extends ServiceImpl<ChinaCityLevel4Mapper, ChinaCityLevel4> implements IChinaCityLevel4Service {

    @Override
    public List<Tree<String>> generateCityTree() {
        List<ChinaCityLevel4> chinaCityLevel4s = CollUtil.newArrayList(this.list());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setParentIdKey("pid");
        treeNodeConfig.setDeep(4);
        return TreeUtil.build(
                chinaCityLevel4s,
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
