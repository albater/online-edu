package com.service.sms.service.impl;

import com.atguigu.guli.common.util.utils.FormUtils;
import com.atguigu.guli.common.util.utils.RandomUtils;
import com.service.base.consts.ServiceConsts;
import com.service.base.exception.GlobalException;
import com.service.base.result.ResultCodeEnum;
import com.service.sms.confiig.SmsProperties;
import com.service.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.plugin2.main.client.MessagePassingOneWayJSObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author alpha
 * @className: SmsServiceImpl
 * @date 2022/8/1 18:18
 * @Description
 */
@Service
@EnableConfigurationProperties(SmsProperties.class)
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsProperties smsProperties;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void sendMsg(String mobile) {
        //1. 判断手机格式
        if (!FormUtils.isMobile(mobile)) {
            throw new GlobalException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }
        //2. 获取验证码频率判断 2分钟内只能获取一次  每天只能获取三次
        if (redisTemplate.hasKey("sms:per:mins" + mobile)) {
            throw new GlobalException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }
        Object obj = redisTemplate.opsForValue().get("sms:per:day" + mobile);

        if (obj == null) {
            redisTemplate.opsForValue().set("sms:per:day" + mobile, 0, 24, TimeUnit.HOURS);
        } else {
            int count = Integer.parseInt(obj.toString());
            //获取验证码次数大于三
            if (count >= 3) {
                throw new GlobalException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_DAY_CONTROL);
            }
            System.out.println("count = " + count);
        }

        //获取验证码次数0~3
        try {
            //生成验证码
            String code = RandomUtils.getSixBitRandom();
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + smsProperties.getAppcode());
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("mobile", mobile);
            querys.put("param", "code:"+code);
            querys.put("tpl_id", smsProperties.getTplId());
//            Map<String, String> bodys = new HashMap<String, String>();
            //缓存验证码到redis中15分钟
            redisTemplate.opsForValue().set(ServiceConsts.SMS_CODE_PREFIX+mobile,code,15,TimeUnit.MINUTES);
            //2分钟只能获取一次验证码
            redisTemplate.opsForValue().set(ServiceConsts.SMS_PER_MINS_PREFIX+mobile,1,2,TimeUnit.MINUTES);
            //验证码消费次数加1
            redisTemplate.opsForValue().increment(ServiceConsts.SMS_PER_DAY_PREFIX+mobile);

        } catch (Exception e) {

            throw new GlobalException(ResultCodeEnum.SMS_SEND_ERROR);
        }

    }
}
