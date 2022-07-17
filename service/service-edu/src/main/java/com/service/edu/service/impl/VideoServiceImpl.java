package com.service.edu.service.impl;

import com.service.edu.entity.Video;
import com.service.edu.mapper.VideoMapper;
import com.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
