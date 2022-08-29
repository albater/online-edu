package com.service.cms.service.impl;

import com.service.cms.entity.Ad;
import com.service.cms.mapper.AdMapper;
import com.service.cms.service.AdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-08-01
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(value = "ads",key = "'cache'")
    @Override
    public Map<String, List> getHotAds() {
        //1.判断redis中是否有缓存
        Object obj = redisTemplate.opsForValue().get("ads:cache");
        if (obj!=null) {
            return (Map<String, List>) obj;
        }
        //2. 没有缓存，查询数据库
        Map<String, List> map = new HashMap<>();
        List<Ad> ads = this.list();
        map.put("banners",ads);

        //3. 将数据存到缓存中
        redisTemplate.opsForValue().set("ads:cache",map,200, TimeUnit.MINUTES);
        return map;
    }
}
