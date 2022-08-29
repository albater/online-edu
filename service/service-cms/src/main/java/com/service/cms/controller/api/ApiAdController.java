package com.service.cms.controller.api;


import com.service.base.result.R;
import com.service.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 广告推荐 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-08-01
 */
@RestController
@CrossOrigin
@RequestMapping("/api/cms/ad")
public class ApiAdController {
    @Autowired
    AdService adService;

    @GetMapping("/getAds")
    public R getAds(){
        Map<String, List> map = adService.getHotAds();
        return R.ok().data("map",map);
    }
}

