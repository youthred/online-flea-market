package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.sys.SysPermission;
import net.add1s.ofm.pojo.vo.sys.SysPermissionVO;

import java.util.List;
import java.util.Set;

public interface ISysPermissionService extends IService<SysPermission> {

    List<String> findPermissionCodesByRoleCodes(List<String> roleCodes);

    Set<SysPermissionVO> findSysPermissionVOByUsername(String username);
}
