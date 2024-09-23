package com.briup.cms.mapper.ext;

import com.briup.cms.bean.basic.Category;
import com.briup.cms.bean.excel.CategoryData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-13 16:40
 */
public interface CategoryExtMapper {
    Category selectByCategoryName(@Param("name") String name);
    List<CategoryData> selectAll();
}
