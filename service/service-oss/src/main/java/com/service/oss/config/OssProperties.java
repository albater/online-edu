package com.service.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author alpha
 * @className: OssProperties
 * @date 2022/7/22 17:15
 * @Description
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    String bucketName;
}
