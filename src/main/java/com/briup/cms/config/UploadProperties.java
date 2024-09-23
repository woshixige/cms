package com.briup.cms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file.upload")
public class UploadProperties {
    private String dir;//dir值通过读取配置file.upload.dir值赋值给组件对象属性
    private String ip;
    private String port;
    private String url;

    /**
     * 获取访问图片的路径前缀
     * @return
     */
    public String getBaseUrl(){
        StringBuilder sb = new StringBuilder();
        //http://localhost:8000/images/test.png
        return sb.append("http://")
                    .append(ip).append(":")
                    .append(port).append(url)
                    .append("/").toString();
    }
}
