package com.service.sms.confiig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author alpha
 * @className: SmsProperties
 * @date 2022/8/1 17:51
 * @Description
 */
@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties {
    private String host;
    private String path;
    private String method;
    private String appcode;
    private String tplId;
}
