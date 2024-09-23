package com.briup.cms.web.controller;

import com.alibaba.excel.EasyExcel;
import com.briup.cms.mapper.basic.CategoryMapper;

import com.briup.cms.service.ICategoryService;
import com.briup.cms.util.Result;
import com.briup.cms.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-12
 */
@Api(tags = "栏目模块")
@RestController
@RequestMapping("/auth/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @ApiOperation("导出栏目数据")
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws Exception{
        //设置响应头：响应体内容以附件的方式下载
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("栏目信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //将文件信息作为响应体内容响应
        categoryService.exportData(response.getOutputStream());

    }
    @ApiOperation("导入栏目数据")
    @PostMapping("/import")
    public Result importData(@RequestPart MultipartFile file) throws Exception {
        //只有方法不抛出异常，执行成功
        categoryService.importData(file.getInputStream());
        return ResultUtil.success();
    }

}
