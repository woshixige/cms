package com.briup.cms.bean.ext;

import com.briup.cms.bean.basic.Article;
import com.briup.cms.bean.basic.Comment;
import com.briup.cms.bean.basic.User;
import lombok.Data;

import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-18 14:39
 */
@Data
public class ArticleExt extends Article {
    private List<Comment> comments;
    private User author;
}
