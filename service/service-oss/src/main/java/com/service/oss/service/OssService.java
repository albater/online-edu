package com.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author alpha
 * @className: OssService
 * @date 2022/7/22 14:58
 * @Description
 */
public interface OssService {
    /**
     * @param file
     * @description: 上传文件
     * @return: java.lang.String
     * @author: alpha
     * @date: 2022/7/22 15:01
     */
    String upload(MultipartFile file,String module);
    /**
     * @param path
     * @param module
     * @description: 删除文件
     * @return: void
     * @author: alpha
     * @date: 2022/7/22 17:07
     */
    void delete(String path, String module);
}
