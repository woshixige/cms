package com.briup.cms.bean.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ConverterUtils;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.briup.cms.bean.basic.Category;
import com.briup.cms.exception.CustomException;
import com.briup.cms.mapper.basic.CategoryMapper;
import com.briup.cms.util.BriupBeanUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.briup.cms.util.ResultCode.CATEGORY_NOT_FIND;


// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class CategoryDataListener implements ReadListener<CategoryData> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<Category> cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */

    private CategoryMapper categoryMapper;

    public CategoryDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数

    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param categoryMapper
     */
    public CategoryDataListener(CategoryMapper categoryMapper) {
        this.categoryMapper =  categoryMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param categoryData    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(CategoryData categoryData, AnalysisContext context) {
        //log.info("解析到一条数据:{}", JSON.toJSONString(data));
        log.info("解析到一条数据:{}", categoryData);
        //1.将CatagoryData对象转化为category
        Category category = BriupBeanUtils.copyBean(categoryData, Category.class);
        //根据名称找到对应的父id
        //利用链式调用的wrapper
        Category parent = new LambdaQueryChainWrapper<>(categoryMapper)
                .eq(Category::getName, categoryData.getParentName())
                .oneOpt()
                .orElseThrow(() -> new CustomException(CATEGORY_NOT_FIND));

        category.setParentId(parent.getId());
        //把数据存放到集合中
        cachedList.add(category);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedList.size());
        categoryMapper.insert(cachedList);
        log.info("存储数据库成功！");
    }
    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }
    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        //map-->list-->readCellData-->String-->List<String>
        /*List<String> headList = headMap.values().stream().map(readCellData -> readCellData.getStringValue()).collect(Collectors.toList());
        log.info("表格头信息：{}",headList);*/
        // 如果想转成成 Map<Integer,String>
        // 方案1： 不要implements ReadListener 而是 extends AnalysisEventListener
        // 方案2： 调用 ConverterUtils.convertToStringMap(headMap, context) 自动会转换
        Map<Integer, String> map = ConverterUtils.convertToStringMap(headMap, context);
        System.out.println(map);
    }
}