package com.service.vod.controller.admin;

import com.service.base.result.R;
import com.service.vod.service.VodService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author alpha
 * @className: VodController
 * @date 2022/7/29 21:00
 * @Description
 */
@RestController
@RequestMapping("/admin/vod")
@CrossOrigin
@Slf4j
@Api("阿里云VOD模块")
public class VodController {
    @Autowired
    private VodService vodService;
    //上传接口
    @PostMapping("/upload")
    public R upload(MultipartFile video) {
        String videoId = vodService.upload(video);
        return R.ok().data("item", videoId);
    }
    //查找视频播放凭证
    @GetMapping("/getPlayAuth/{videoId}")
    public R getPlayAuth(@PathVariable("videoId") String videoId){
        String playAuth = vodService.getPlayAuth(videoId);
        return R.ok().data("playAuth",playAuth);
    }
}
