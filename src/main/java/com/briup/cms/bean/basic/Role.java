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
 * @since 2024-09-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_role")
@ApiModel(value = "Role对象", description = "")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("角色描述")
    @TableField("description")
    private String description;
}
