package net.add1s.ofm.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.service.ISysPermissionService;
import net.add1s.ofm.service.IUserHomeAdminAuthPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UserHomeAdminAuthPermissionServiceImpl implements IUserHomeAdminAuthPermissionService {

    private final ISysPermissionService iSysPermissionService;

    @Override
    public IPage<SysPermission> page(MbpPage<SysPermission> mbpPage) {
        return iSysPermissionService.page(mbpPage.getPage(), mbpPage.toQueryWrapper());
    }

    @Override
    public List<Tree<String>> tree() {
        List<SysPermission> chinaCities = CollUtil.newArrayList(iSysPermissionService.list());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setParentIdKey("pid");
        treeNodeConfig.setDeep(10);
        return TreeUtil.build(
                chinaCities,
                "-1",
                treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getTbId().toString());
                    tree.setParentId(treeNode.getPid().toString());
                    tree.setName(treeNode.getPermissionName());
                    tree.putExtra("open", true);
                    tree.putExtra("sysPermission", treeNode);
                }
        );
    }
}
