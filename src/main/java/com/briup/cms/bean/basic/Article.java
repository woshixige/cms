package com.briup.cms.bean.basic;

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
 * @since 2024-09-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_article")
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章id")
    @TableId("id")
    private Long id;

    @ApiModelProperty("文章标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("文章内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("文章审核状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("阅读量")
    @TableField("read_num")
    private Integer readNum;

    @ApiModelProperty("点赞量")
    @TableField("like_num")
    private Integer likeNum;

    @ApiModelProperty("拉踩量")
    @TableField("dislike_num")
    private Integer dislikeNum;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("类别id")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("是否收费，默认0不收费")
    @TableField("charged")
    private Integer charged;

    @ApiModelProperty("文章删除状态")
    @TableField("deleted")
    private Integer deleted;

    @ApiModelProperty("文章发表时间")
    @TableField("publish_time")
    private LocalDateTime publishTime;
}
