package com.service.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.service.base.exception.GlobalException;
import com.service.base.result.ResultCodeEnum;
import com.service.oss.config.OssProperties;
import com.service.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author alpha
 * @className: OssServiceImpl
 * @date 2022/7/22 14:58
 * @Description
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Autowired
    private OssProperties ossProperties;
    /**
     * @param file 文件
    * @param module 目录
     * @description: 根据目录上传文件
     * @return: java.lang.String
     * @author: alpha
     * @date: 2022/7/23 9:48
     */
    @Override
    public String upload(MultipartFile file, String module) {


        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。

        String filePath = file.getOriginalFilename();

        String fileName = UUID.randomUUID().toString().replace("-", "")
                + filePath.substring(filePath.lastIndexOf("."));
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "avatar/test.jpg";
        String objectName = module + new DateTime().toString("/yyyy-MM-dd/") + fileName;
//        return  fileName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());

        try {
            // 获取文件的输入流
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            ossClient.putObject(ossProperties.getBucketName(), objectName, inputStream);
//            上传文件后的地址： https://xxguli-file.oss-cn-shanghai.aliyuncs.com/avatar/2022-07-22/5af742e0926e4326b069c01a6f7a4826.jpg
            String uploadPath = "https://" + ossProperties.getBucketName() + ".oss-cn-shanghai.aliyuncs.com/" + objectName;
            // 返回上传后的地址
            return uploadPath;
        } catch (Exception ce) {
            System.out.println("Error Message:" + ce.getMessage());
            log.error(ce.getMessage());
            throw new GlobalException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public void delete(String path, String module) {
//         要删除的地址： https://xxguli-file.oss-cn-shanghai.aliyuncs.com/avatar/2022-07-22/5af742e0926e4326b069c01a6f7a4826.jpg
        // 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        // 也就是说剔除     https://xxguli-file.oss-cn-shanghai.aliyuncs.com/
        String objectName = path.substring(path.lastIndexOf(module));
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());

        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(ossProperties.getBucketName(), objectName);
        } catch (ClientException ce) {
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


}
