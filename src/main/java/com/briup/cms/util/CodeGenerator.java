package com.briup.cms.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Collections;

/**
 * 生成代码：
 * 指定哪个表生成对应的代码
 */
public class CodeGenerator {
    //根据自己实际项目信息，修改配置内容即可
    private static String url = "jdbc:mysql://localhost:3306/cms";
    private static String username = "root";
    private static String password = "1234";
    private static String basePackage = "com.briup.cms";

    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> builder
                        .author("zhangzx")
                        .disableOpenDir() //生成文件后，不需要自动打开
                        .enableSwagger() //开启swagger注解
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                )
                //包配置
                .packageConfig(builder -> builder
                        .parent(basePackage) //指定父包名
                        .entity("bean.basic") //实现类包
                        .mapper("mapper.basic") //dao层
                        .service("service")//service层
                        .serviceImpl("service.impl")//service层
                        .controller("web.controller")//web层
                        .xml("mapper.xml")
                        //指定xml文件的生成路径
                        .pathInfo(Collections.singletonMap(
                                OutputFile.xml,Paths.get(System.getProperty("user.dir"))
                                        + "/src/main/resources/mapper/basic" ))
                )
                //策略配置： 生成代码的表信息
                .strategyConfig(builder -> builder
                        //模糊匹配表名
                        //.likeTable(new LikeTable("manager_"))
                        //增加表匹配
                        .addInclude("cms_user")
                        //去掉生成实体类的前缀
                        .addTablePrefix("cms_")
                        //去掉生成字段前缀
                        .addFieldPrefix("s_")
                )

                //策略配置: 对指定的实体类进行策略配置
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok() //开启lombok
                        .enableFileOverride() //覆盖已生成文件
                        .enableChainModel() //开启链式调用
                        .enableTableFieldAnnotation() //开启生成实体时生成字段注解

                )
                //controller配置
                .strategyConfig(builder -> builder
                        .controllerBuilder()
                        .enableRestStyle() //开启生成@RestController 控制器
                        .enableFileOverride() //覆盖已生成文件
                )
                //service配置
                .strategyConfig(builder -> builder
                        .serviceBuilder()
                        //.enableFileOverride() //覆盖已生成文件
                )
                //mapper
                .strategyConfig(builder -> builder
                        .mapperBuilder()
                        .enableFileOverride()
                        .enableBaseResultMap()//生成resultMap
                        .enableBaseColumnList()//启用 BaseColumnList
                )
                //默认模板代码
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
