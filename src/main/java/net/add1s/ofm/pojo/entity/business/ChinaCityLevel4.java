package net.add1s.ofm.pojo.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@TableName("t_china_city_level4")
public class ChinaCityLevel4 implements Serializable {

    private static final long serialVersionUID = 8683045414452921591L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    @TableField("`id`")
    private Long id;

    @TableField("`pid`")
    private Long pid;

    @TableField("`deep`")
    private Integer deep;

    @TableField("`name`")
    private String name;

    @TableField("`pinyin_prefix`")
    private char pinyinPrefix;

    @TableField("`pinyin`")
    private String pinyin;

    @TableField("`ext_id`")
    private Long extId;

    @TableField("`ext_name`")
    private String extName;
}
