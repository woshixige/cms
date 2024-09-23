package com.briup.cms.service.impl;

import com.alibaba.excel.EasyExcel;
import com.briup.cms.bean.basic.Category;
import com.briup.cms.bean.excel.CategoryData;
import com.briup.cms.bean.excel.CategoryDataListener;
import com.briup.cms.mapper.basic.CategoryMapper;
import com.briup.cms.mapper.ext.CategoryExtMapper;
import com.briup.cms.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryExtMapper categoryExtMapper;
    @Override
    public void exportData(ServletOutputStream outputStream) {
        List<CategoryData> categoryList = categoryExtMapper.selectAll();
        EasyExcel.write(outputStream, CategoryData.class).sheet().doWrite(categoryList);
    }

    @Override
    public void importData(InputStream inputStream) {
        //1.自定义一个监听器，实现解析和读取数据
        CategoryDataListener listener = new CategoryDataListener(categoryMapper);
        //2.导入数据
        EasyExcel.read(inputStream,CategoryData.class,listener).sheet().doRead();
    }
}
