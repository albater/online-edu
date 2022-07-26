package com.service.edu.feign.fallback;

import com.service.base.result.R;
import com.service.edu.feign.OssClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @author alpha
 * @className: FallbackOssClient
 * @date 2022/7/25 20:03
 * @Description
 */
@Service
@Slf4j
public class FallbackOssClient implements OssClient {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public R test() {
        return null;
    }

    @Override
    public R delete(String path, String module) {
        BoundHashOperations<String, Object, Object> boundHashOps = stringRedisTemplate.boundHashOps("teacher:delfail:avatar");
        boundHashOps.put(path,module);
        return null;
    }
}
