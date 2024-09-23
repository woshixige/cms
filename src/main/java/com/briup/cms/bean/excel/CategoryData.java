package com.briup.cms.bean.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.briup.cms.bean.basic.Category;
import com.briup.cms.mapper.ext.CategoryExtMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryData{


    @ExcelProperty("栏目名称")
    private String name;


    @ExcelProperty("栏目描述")
    private String description;


    @ExcelProperty("栏目序号")
    private Integer orderNum;


    @ExcelProperty(value = "栏目删除状态",converter = StatusStringConverter.class)
    //@ExcelProperty(value = "栏目删除状态")
    private Integer deleted;



    @ExcelProperty(value = "父栏目")
    private String parentName;
    public static class StatusStringConverter implements Converter<Integer> {

        /**
         * 这里读的时候会调用
         *
         * @param context
         * @return
         */
        @Override
        public Integer convertToJavaData(ReadConverterContext<?> context) {
            //将读取到的字符串需要将栏目删除状态由 被删除 或 未被删除 转换为 1或0
            String status = context.getReadCellData().getStringValue();
            if ("被删除".equals(status)){
                return Integer.parseInt(status.replace(status,"1")) ;
            }else{
                return Integer.parseInt(status.replace(status,"0"));
            }
        }
        @Override
        public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
            //逻辑处理0 和 1 显示问题
            String deleted = value == 0 ? "未删除":"已删除";
            //在spring中如何使用Bean对象？ 只能在其他bean对象注入使用bean对象
            //如何在任意的位置获取到一个Bean对象?


            return new WriteCellData<>(deleted);
        }
    }

}
