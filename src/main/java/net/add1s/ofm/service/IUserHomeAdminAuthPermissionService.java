package net.add1s.ofm.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.sys.SysPermission;

import java.util.List;

public interface IUserHomeAdminAuthPermissionService {

    IPage<SysPermission> page(MbpPage<SysPermission> mbpPage);

    List<Tree<String>> tree();
}
