package com.briup.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.bean.basic.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.briup.cms.bean.ext.ArticleExt;
import com.briup.cms.service.dto.ArticleDTO;
import com.briup.cms.vo.ArticleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-10
 */
public interface IArticleService extends IService<Article> {

    IPage<ArticleExt> selectPage(Integer pageNum,Integer pageSize);

    /**
     * 多表分页多条件查询
     * @param vo
     * @return
     */
    IPage<ArticleDTO> findByPage(ArticleVO vo);

    /**
     * 根据文章编号查询文章相关信息
     * @param id
     * @return
     */
    ArticleExt findById(Integer id);
}
