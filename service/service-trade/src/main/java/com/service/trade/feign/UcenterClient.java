package com.service.trade.feign;

import com.service.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author alpha
 * @className: UcenterClient
 * @date 2022/8/5 20:40
 * @Description
 */
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/api/ucenter/member/getMemberDto/{id}")
    public R getMemberDto(@PathVariable("id")String id);
}
