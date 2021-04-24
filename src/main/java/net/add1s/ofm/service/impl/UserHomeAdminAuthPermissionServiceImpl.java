package net.add1s.ofm.service.impl;

import lombok.AllArgsConstructor;
import net.add1s.ofm.service.IUserHomeAdminAuthPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UserHomeAdminAuthPermissionServiceImpl implements IUserHomeAdminAuthPermissionService {
}
