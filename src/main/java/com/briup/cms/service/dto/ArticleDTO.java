package com.briup.cms.service.dto;

import com.briup.cms.bean.basic.Article;
import com.briup.cms.bean.basic.Comment;
import com.briup.cms.bean.basic.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-19 9:23
 *
 * service层和其他层进行数据传输
 */
@Accessors(chain = true)
@Data
public class ArticleDTO extends Article {
    private User author;
    private List<Comment> comments;
}
