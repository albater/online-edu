package com.service.sms.controller;

import com.service.base.result.R;
import com.service.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author alpha
 * @className: SmsController
 * @date 2022/8/1 18:03
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/api/sms")
public class ApiSmsController {
    @Autowired
    private SmsService smsService;

    @GetMapping("/sendMsg/{mobile}")
    public R sendMsg(@PathVariable("mobile") String mobile){
        smsService.sendMsg(mobile);
        return R.ok();
    }
}
