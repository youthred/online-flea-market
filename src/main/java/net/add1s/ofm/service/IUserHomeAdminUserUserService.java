package net.add1s.ofm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.add1s.ofm.common.page.MbpPage;
import net.add1s.ofm.pojo.entity.sys.SysUser;
import net.add1s.ofm.pojo.vo.sys.SysRoleVO;

import java.util.List;

public interface IUserHomeAdminUserUserService {

    IPage<SysUser> page(MbpPage<SysUser> mbpPage);

    void resetPassword(Long sysUserTbId);

    void enableOrDisable(Long sysUserTbId, Boolean enableState);

    List<SysRoleVO> roles(Long sysUserTbId);

    void roleBind(List<SysRoleVO> sysRoleVOS);
}
