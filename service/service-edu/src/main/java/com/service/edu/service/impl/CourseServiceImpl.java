package com.service.edu.service.impl;

import com.service.edu.entity.Course;
import com.service.edu.mapper.CourseMapper;
import com.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
