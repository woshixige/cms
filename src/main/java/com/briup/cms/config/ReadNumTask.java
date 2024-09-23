package com.briup.cms.config;

import com.briup.cms.bean.basic.Article;
import com.briup.cms.mapper.basic.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-23 23:19
 */

/**
 * 开启定时任务，每五秒将缓存数据，保存在数据库中
 */
@EnableScheduling
@Configuration
@Slf4j
public class ReadNumTask {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ArticleMapper articleMapper;
    @Scheduled(cron = "*/5 * * * * *")
    public void saveReadNum(){
        log.info("开始批量保存");
        //获取redis缓存中的数据
        Map<String,String> map = redisTemplate.opsForHash().entries("readNum");
        List<Article> articleList = map.entrySet().stream().map(entry -> new Article()
                        .setId(Long.parseLong(entry.getKey()))
                        .setReadNum(Integer.parseInt(entry.getValue())))
                .collect(Collectors.toList());
        //将数据保存到mysql数据库中
        articleMapper.updateById(articleList);
    }
}
