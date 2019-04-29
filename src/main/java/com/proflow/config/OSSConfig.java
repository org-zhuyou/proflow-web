package com.proflow.config;

import com.aliyun.oss.OSSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Leonid on 2019/4/29.
 */
@Configuration
public class OSSConfig {

    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "LTAIdu7NXsK2tE5f";
    private static final String accessKeySecret = "aJAaLuGbCUfRgO1HaaNBjlaaF9cimx";
    //private static final String bucketName = "proflow";

    @Bean
    public OSSClient ossClient() {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }

}
