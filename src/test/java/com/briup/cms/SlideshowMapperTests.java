package com.briup.cms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.bean.basic.Slideshow;
import com.briup.cms.mapper.basic.SlideshowMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-10 14:57
 */
@SpringBootTest
public class SlideshowMapperTests {
    @Autowired
    private SlideshowMapper slideshowMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void method() throws JsonProcessingException {
        /*Wrappers.lambdaQuery(Slideshow.class)
                .eq(Slideshow::getId,1);*/
        IPage<Slideshow> pageInfo = new Page<>(1,2,true);
        IPage<Slideshow> page = slideshowMapper.selectPage(pageInfo, null);
        String json = objectMapper.writeValueAsString(page);
        System.out.println(json);
    }
}
