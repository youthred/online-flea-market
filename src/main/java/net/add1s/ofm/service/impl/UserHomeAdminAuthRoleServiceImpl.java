package net.add1s.ofm.service.impl;

import lombok.AllArgsConstructor;
import net.add1s.ofm.service.IUserHomeAdminAuthRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UserHomeAdminAuthRoleServiceImpl implements IUserHomeAdminAuthRoleService {
}
