package com.service.edu.feign;

import com.service.base.result.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author alpha
 * @className: OssClient
 * @date 2022/7/25 19:42
 * @Description
 */
@FeignClient(value = "service-oss")
public interface OssClient {
    @GetMapping("/admin/oss/test")
    public R test();

    @DeleteMapping("/admin/oss/delete")
    public R delete(@RequestParam("path") String path,
                    @RequestParam("module") String module);
}
