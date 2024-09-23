package com.briup.cms.bean.basic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("cms_log")
@ApiModel(value = "Log对象", description = "")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("访问用户账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("接口描述信息")
    @TableField("business_name")
    private String businessName;

    @ApiModelProperty("请求的地址")
    @TableField("request_url")
    private String requestUrl;

    @ApiModelProperty("请求的方式，get post delete put")
    @TableField("request_method")
    private String requestMethod;

    @ApiModelProperty("ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("ip来源")
    @TableField("source")
    private String source;

    @ApiModelProperty("请求接口耗时")
    @TableField("spend_time")
    private Long spendTime;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("请求的参数")
    @TableField("params_json")
    private String paramsJson;

    @ApiModelProperty("响应参数")
    @TableField("result_json")
    private String resultJson;
}
