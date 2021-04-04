package net.add1s.ofm.pojo.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@TableName("t_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = -8506606304323757616L;

    @TableId(value = "`tb_id`", type = IdType.AUTO)
    private Long tbId;

    @TableField("`create_time`")
    private LocalDateTime createTime;

    @TableField("`update_time`")
    private LocalDateTime updateTime;

    /**
     * 价格
     */
    @TableField("`price`")
    private Double price;

    /**
     * 商品描述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 图片地址，以“|”分隔
     */
    @TableField("`pics`")
    private String pics;

    /**
     * 浏览量
     */
    @TableField("`views`")
    private Long views;

    /**
     * 发布地城市ID
     */
    @TableField("`city_id`")
    private Long cityId;

    /**
     * 卖家TBID
     */
    @TableField("`seller_sys_user_tb_id`")
    private Long sellerSysUserTbId;

    /**
     * 是否已下架
     */
    @TableField("`off_shelf`")
    private Boolean offShelf;

    /**
     * 是否已删除
     */
    @TableField("`deleted`")
    private Boolean deleted;
}
