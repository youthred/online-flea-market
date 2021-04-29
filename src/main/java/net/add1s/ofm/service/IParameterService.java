package net.add1s.ofm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.add1s.ofm.pojo.entity.business.Parameter;
import net.add1s.ofm.pojo.vo.business.ParameterVO;

import java.util.List;

public interface IParameterService extends IService<Parameter> {

    List<ParameterVO> usedBookTypes();
}
