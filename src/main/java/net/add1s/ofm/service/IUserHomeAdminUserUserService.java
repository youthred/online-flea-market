package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.sys.SysRole;
import net.add1s.ofm.pojo.entity.sys.SysUser;

import java.util.List;

public interface IUserHomeAdminUserUserService {

    IPage<SysUser> page(MbpPage<SysUser> mbpPage);

    void resetPassword(Long sysUserTbId);

    void enableOrDisable(Long sysUserTbId, Boolean enableState);

    List<SysRole> boundRoles(Long sysUserTbId);
}
