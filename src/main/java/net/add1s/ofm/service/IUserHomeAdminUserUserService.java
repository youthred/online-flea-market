package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.sys.SysUser;

public interface IUserHomeAdminUserUserService {

    IPage<SysUser> page(MbpPage<SysUser> mbpPage);

    void resetPassword(Long sysUserTbId);

    void enableOrDisable(Long sysUserTbId, Boolean enableState);
}
