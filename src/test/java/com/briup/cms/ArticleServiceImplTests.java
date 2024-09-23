package com.briup.cms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.service.IArticleService;
import com.briup.cms.service.dto.ArticleDTO;
import com.briup.cms.vo.ArticleVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-19 10:10
 */
@SpringBootTest
public class ArticleServiceImplTests {
    @Autowired
    private IArticleService articleService;
    @Test
    public void method(){
        ArticleVO articleVO = new ArticleVO();
        articleVO.setTitle("测试文章");
        articleVO.setPageNum(1);
        articleVO.setPageSize(5);
        IPage<ArticleDTO> page = articleService.findByPage(articleVO);
        System.out.println(page);
    }
}
