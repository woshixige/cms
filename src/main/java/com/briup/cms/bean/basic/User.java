package com.briup.cms.bean.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@TableName("cms_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty("用户名称")
    @TableField("username")
    private String username;

    @ApiModelProperty("用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("用户头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("性别")
    @TableField("gender")
    private String gender;

    @ApiModelProperty("用户邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("用户电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("注册时间")
    @TableField("register_time")
    private LocalDateTime registerTime;

    @ApiModelProperty("用户状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("是否为会员")
    @TableField("is_vip")
    private Integer isVip;

    @ApiModelProperty("会员到期时间")
    @TableField("expires_time")
    private LocalDateTime expiresTime;

    @ApiModelProperty("用户删除状态")
    @TableField("deleted")
    private Integer deleted;
}
