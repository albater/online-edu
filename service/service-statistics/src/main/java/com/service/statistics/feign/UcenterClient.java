package com.service.statistics.feign;

import com.service.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author alpha
 * @className: UcenterClient
 * @date 2022/8/8 20:03
 * @Description
 */
@FeignClient(value = "service-ucenter")
public interface UcenterClient {
    @GetMapping("/admin/ucenter/member/getRegisterNum/{day}")
    public R getRegisterNum(@PathVariable("day") String day);
}
