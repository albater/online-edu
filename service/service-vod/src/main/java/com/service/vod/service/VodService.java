package com.service.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author alpha
 * @className: VodService
 * @date 2022/7/29 21:03
 * @Description
 */
public interface VodService {
    String upload(MultipartFile video);

    String getPlayAuth(String videoId);
}
