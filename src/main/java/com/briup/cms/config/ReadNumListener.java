package com.briup.cms.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.briup.cms.bean.basic.Article;
import com.briup.cms.mapper.basic.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.annotation.WebFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-19 14:06
 * 文章阅读量监听器
 */
@Slf4j
@Configuration
public class ReadNumListener {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @EventListener(ApplicationStartedEvent.class)
    public void initReadNum(){
        log.info("启动项目，初始化redis数据");
        //获取文章的编号和文章阅读量
        LambdaQueryWrapper<Article> wrapper = Wrappers
                .lambdaQuery(Article.class)
                .select(Article::getId,Article::getReadNum);
        List<Map<String, Object>> list = articleMapper.selectMaps(wrapper);
        log.info(list.toString());
        /*
        [{read_num=0, id=1}, {read_num=0, id=2}, {read_num=0, id=3}, {read_num=0, id=4}]
        [{1=0},{2=0}...]
         */
        Map<Object, Object> map = list.stream()
                .collect(Collectors
                .toMap(m -> m.get("id").toString(), m -> m.get("read_num").toString()));
        log.info(map.toString());
        //将文章编号对应的阅读量保存到redis中
        HashOperations ops = redisTemplate.opsForHash();
        ops.putAll("readNum",map);

    }
}
