package com.service.vod.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author alpha
 * @className: VodProperties
 * @date 2022/7/29 20:17
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod")
public class VodProperties {
    private String keyId;
    private String keySecret;
    private String templateGroupId;
    private String workflowId;
}
