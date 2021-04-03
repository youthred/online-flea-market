package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.mapper.ParameterMapper;
import net.add1s.ofm.pojo.entity.business.Parameter;
import net.add1s.ofm.service.IParameterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements IParameterService {
}
