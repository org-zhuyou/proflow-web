package com.proflow.service;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Leonid on 2019/4/29.
 */
public interface OSSObjectService {

    String uploadFile2OSS(File file) throws Exception;

    String uploadByte2OSS(byte[] bytes, String filename) throws Exception;

    String uploadInputStream2OSS(InputStream inputStream, String filename) throws Exception;

}
