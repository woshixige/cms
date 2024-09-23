package com.briup.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.briup.cms.bean.basic.Article;
import com.briup.cms.bean.basic.Comment;
import com.briup.cms.bean.basic.User;
import com.briup.cms.bean.ext.ArticleExt;
import com.briup.cms.mapper.basic.ArticleMapper;
import com.briup.cms.mapper.basic.CommentMapper;
import com.briup.cms.mapper.basic.UserMapper;
import com.briup.cms.mapper.ext.ArticleExtMapper;
import com.briup.cms.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.briup.cms.service.dto.ArticleDTO;
import com.briup.cms.util.BriupBeanUtils;
import com.briup.cms.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-10
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private ArticleExtMapper articleExtMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IPage<ArticleExt> selectPage(Integer pageNum, Integer pageSize) {
        Page<ArticleExt> page = new Page<>(pageNum, pageSize, true);
        List<ArticleExt> articleExtList = articleExtMapper.selectPage();
        return page;
    }

    @Override
    public IPage findByPage(ArticleVO vo) {
        Page<Article> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        LambdaQueryWrapper<Article> wrapper = Wrappers.lambdaQuery(Article.class).like(Article::getTitle, vo.getTitle());
        List<Article> list = Db.list(page, wrapper);
        list.stream()
                .map(article -> BriupBeanUtils.copyBean(article, ArticleDTO.class))
                .map(articleDTO -> {
                    User user = userMapper.selectById(articleDTO.getId());
                    articleDTO.setAuthor(user);
                    return articleDTO;
                })
                .map(articleDTO -> {
                    LambdaQueryWrapper<Comment> wrapper2 = Wrappers.lambdaQuery(Comment.class).eq(Comment::getArticleId, articleDTO.getId());
                    List<Comment> comments = commentMapper.selectList(wrapper2);
                    articleDTO.setComments(comments);
                    return articleDTO;
                })
                .collect(Collectors.toList());
        page.setRecords(list);
        return page;
    }

    @Override
    public ArticleExt findById(Integer id) {
        List<Article> list = Db.list(Wrappers.lambdaQuery(Article.class).eq(Article::getId, id));
        List<ArticleExt> articleExtList = list.stream().map(article -> BriupBeanUtils.copyBean(article, ArticleExt.class))
                .map(articleExt -> {
                    //查询文章
                    // 对应的评论
                    List<Comment> commentList = Db.list(Wrappers.lambdaQuery(Comment.class).eq(Comment::getArticleId, articleExt.getId()));
                    articleExt.setComments(commentList);
                    return articleExt;
                })
                .map(articleExt -> {
                    User user = Db.lambdaQuery(User.class).eq(User::getId, articleExt.getUserId()).one();
                    articleExt.setAuthor(user);
                    return articleExt;
                })
                .collect(Collectors.toList());
        ArticleExt articleExt = articleExtList.get(0);
        if (articleExt != null) {
            Long readNum = redisTemplate.opsForHash().increment("readNum", id.toString(), 1);
            articleExt.setReadNum(readNum.intValue());

        }
        return articleExt;
    }
}
