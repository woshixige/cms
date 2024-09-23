package com.briup.cms.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.bean.basic.Slideshow;
import com.briup.cms.service.ISlideshowService;
import com.briup.cms.util.Result;
import com.briup.cms.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-10
 */
@Api(tags = "轮播图模块")
@RestController
@RequestMapping("/slideshow")
public class SlideshowController {
    @Autowired
    private ISlideshowService slideshowService;
    @ApiOperation("多条件分页查询轮播图接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "description",value = "描述信息",required = false),
        @ApiImplicitParam(name = "pageNum",value = "当前页",required = true),
        @ApiImplicitParam(name = "pageSize",value = "每页大小",required = true),
        @ApiImplicitParam(name = "status",value = "状态值",required = false)
    })
    @GetMapping("/query")
    public Result selectByPage(String description, @RequestParam Integer pageNum, @RequestParam Integer pageSize, String status){
        IPage<Slideshow> pageInfo = new Page<>(pageNum,pageSize,true);
        LambdaQueryWrapper<Slideshow> wrapper = Wrappers.lambdaQuery(Slideshow.class)
                .func(i->{
                    if (StringUtils.hasLength(description) && StringUtils.hasLength(status)){
                        i.eq(Slideshow::getDescription,description).eq(Slideshow::getStatus,status);
                    }else if (StringUtils.hasLength(description) && !StringUtils.hasLength(status)){
                        i.eq(Slideshow::getDescription,description);
                    } else if (!StringUtils.hasLength(description) && StringUtils.hasLength(status)) {
                        i.eq(Slideshow::getStatus,status);
                    }
                });
        IPage<Slideshow> page = slideshowService.page(pageInfo, wrapper);
        return ResultUtil.success(page);
    }


    @ApiOperation("根据轮播图的id查询轮播图信息接口")
    @GetMapping("/queryById/{id}")
    public Result findById(@PathVariable Integer id){
        Slideshow slideshow = slideshowService.getById(id);
        return ResultUtil.success(slideshow);
    }
    @ApiOperation("查询所有可用的轮播图的接口")
    @GetMapping("/queryAllEnable")
    public Result queryAllEnable(){
        List<Slideshow> slideshowList = slideshowService.list();
        return ResultUtil.success(slideshowList);
    }
    @ApiOperation("根据编号删除轮播图的接口")
    @DeleteMapping("/deleteByBatch/{ids}")
    public Result deleteByBatch(@PathVariable List<Integer> ids){
        slideshowService.removeByIds(ids);
        return ResultUtil.success();
    }
    @ApiOperation("新增或更新轮播图的接口")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Slideshow slideshow){
        slideshowService.saveOrUpdate(slideshow);
        return ResultUtil.success();
    }


}
