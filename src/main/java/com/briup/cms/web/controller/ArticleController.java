package com.briup.cms.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.bean.ext.ArticleExt;
import com.briup.cms.service.IArticleService;
import com.briup.cms.service.dto.ArticleDTO;
import com.briup.cms.util.Result;
import com.briup.cms.util.ResultUtil;
import com.briup.cms.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-10
 */
@Api(tags = "咨迅模块")
@RestController
@RequestMapping("/auth/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @ApiOperation("分页多条件查询文章信息接口")
    @PostMapping("/query")
    public Result findByPage(@RequestBody ArticleVO vo){

        IPage<ArticleDTO> page=articleService.findByPage(vo);
        return ResultUtil.success(page);
    }
    @ApiOperation("根据文章编号查询文章相关信息接口")
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        ArticleExt articleExt=articleService.findById(id);
        return ResultUtil.success(articleExt);
    }
}
