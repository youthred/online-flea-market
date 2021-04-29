package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.common.content.ParameterGroup;
import net.add1s.ofm.mapper.ParameterMapper;
import net.add1s.ofm.pojo.entity.business.Parameter;
import net.add1s.ofm.pojo.vo.business.ParameterVO;
import net.add1s.ofm.service.IParameterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements IParameterService {

    @Override
    public List<ParameterVO> usedBookTypes() {
        return this.list(Wrappers.lambdaQuery(Parameter.class).eq(Parameter::getGroupName, ParameterGroup.USED_BOOKS_TYPE).orderByAsc(Parameter::getCode)).stream().map(ParameterVO::new).collect(Collectors.toList());
    }
}
