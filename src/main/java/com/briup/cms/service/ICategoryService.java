package com.briup.cms.service;

import com.briup.cms.bean.basic.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-12
 */
public interface ICategoryService extends IService<Category> {

    //导出excel表格数据
    void exportData(ServletOutputStream outputStream);

    //导入excel表格数据
    void importData(InputStream inputStream);
}
