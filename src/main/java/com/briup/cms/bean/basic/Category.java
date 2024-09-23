package com.briup.cms.bean.basic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_category")
@ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("栏目编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("栏目名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("栏目描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("栏目序号")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty("栏目删除状态")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty("父栏目id")
    @TableField("parent_id")
    private Integer parentId;
}
