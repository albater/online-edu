package com.service.edu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.service.edu.entity.vo.AdminCourseItemVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<AdminCourseItemVo> selectCourseItemVoPage(Page<AdminCourseItemVo> itemVoPage);
}
