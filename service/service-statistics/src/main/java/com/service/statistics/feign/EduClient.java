package com.service.statistics.feign;

import com.service.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author alpha
 * @className: EduClient
 * @date 2022/8/8 20:02
 * @Description
 */
@FeignClient(value = "service-edu")
public interface EduClient {
    @GetMapping("/admin/edu/course/getCoursePublishNum/{day}")
    public R getCoursePublishNum(@PathVariable("day") String day);
}
