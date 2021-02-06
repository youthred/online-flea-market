package net.add1s.ofm.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class GoodsReportDTO implements Serializable {

    private static final long serialVersionUID = -1880343715632007429L;

    @NotNull
    private Long goodsTbId;

    /**
     * 举报原因
     */
    @NotBlank
    private String reason;
}
