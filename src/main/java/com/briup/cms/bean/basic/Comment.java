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
 * @since 2024-09-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cms_comment")
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("content")
    private String content;

    @TableField("publish_time")
    private LocalDateTime publishTime;

    @TableField("user_id")
    private Long userId;

    @TableField("article_id")
    private Long articleId;

    @TableField("deleted")
    private Integer deleted;
}
