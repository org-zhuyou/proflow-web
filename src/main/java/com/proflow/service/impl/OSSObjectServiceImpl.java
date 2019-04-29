package com.proflow.service.impl;

import com.aliyun.oss.OSSClient;
import com.proflow.service.OSSObjectService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Leonid on 2019/4/29.
 */
@Service
public class OSSObjectServiceImpl implements OSSObjectService, DisposableBean {

    @Autowired
    private OSSClient ossClient;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.url}")
    private String url;

    @Override
    public String uploadFile2OSS(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        ossClient.putObject(bucketName, file.getName(), new FileInputStream(file));
        return url + file.getName();
    }

    @Override
    public String uploadByte2OSS(byte[] bytes, String filename) throws Exception {
        if (bytes.length == 0) {
            throw new IllegalArgumentException();
        }
        ossClient.putObject(bucketName, filename, new ByteArrayInputStream(bytes));
        return url + filename;
    }

    @Override
    public String uploadInputStream2OSS(InputStream inputStream, String filename) throws Exception {
        if (inputStream == null) {
            throw new IllegalArgumentException();
        }

        ossClient.putObject(bucketName, filename, inputStream);
        return url + filename;
    }


    @Override
    public void destroy() throws Exception {
        ossClient.shutdown();
    }
}
