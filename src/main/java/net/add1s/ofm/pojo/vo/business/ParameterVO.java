package net.add1s.ofm.pojo.vo.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.business.Parameter;

import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ParameterVO implements Serializable {

    private static final long serialVersionUID = -6916796406809336098L;

    public ParameterVO(Parameter parameter) {
        this.tbId = parameter.getTbId();
        this.groupName = parameter.getGroupName();
        this.code = parameter.getCode();
        this.desc = parameter.getDesc();
    }

    private Long tbId;
    private String groupName;
    private String code;
    private String desc;
}
