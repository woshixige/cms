package com.briup.cms.util;

import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OSSUtil {
    private String accessKey;
    private String secretKey;
    private String bucket;//存储空间名

    /**
     * 文件上传
     * @param filename 上传文件名
     * @param in 字节流信息
     */
    public String upload(String filename, InputStream in){
        String newFileName=UUID.randomUUID().toString().concat("-").concat(filename);
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            uploadManager.put(in,newFileName,upToken,null, null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return newFileName;
    }
}
