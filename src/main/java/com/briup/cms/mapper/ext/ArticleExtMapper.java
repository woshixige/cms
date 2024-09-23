package com.briup.cms.mapper.ext;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.bean.ext.ArticleExt;

import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-18 15:13
 */
public interface ArticleExtMapper {
    List<ArticleExt> selectPage();
}
