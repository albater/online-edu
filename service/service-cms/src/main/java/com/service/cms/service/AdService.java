package com.service.cms.service;

import com.service.cms.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-08-01
 */
public interface AdService extends IService<Ad> {

    Map<String, List> getHotAds();

}
