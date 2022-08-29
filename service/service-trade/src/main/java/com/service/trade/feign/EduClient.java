package com.service.trade.feign;

import com.service.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author alpha
 * @className: EduClient
 * @date 2022/8/5 19:56
 * @Description
 */
@FeignClient(value = "service-edu")
public interface EduClient {
    @GetMapping("/api/edu/course/getCourseDto/{id}")
    public R getCourseDto(@PathVariable("id")String id);
}
