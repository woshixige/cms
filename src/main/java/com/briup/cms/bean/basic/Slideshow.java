package com.briup.cms.bean.basic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2024-09-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_slideshow")
@ApiModel(value = "Slideshow对象", description = "")
public class Slideshow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("轮播图编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("轮播图片url")
    @TableField("url")
    private String url;

    @ApiModelProperty("图片状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("删除状态")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty("上传时间")
    @TableField("upload_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadTime;
}
