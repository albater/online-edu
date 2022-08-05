package com.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.edu.entity.Course;
import com.service.edu.entity.CourseDescription;
import com.service.edu.entity.vo.AdminCourseInfoVo;
import com.service.edu.entity.vo.AdminCourseItemVo;
import com.service.edu.mapper.CourseDescriptionMapper;
import com.service.edu.mapper.CourseMapper;
import com.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Override
    public String saveCourseInfo(AdminCourseInfoVo courseInfoVo) {


        //查询课程数据
        Course course = new Course();
        //将courseInfoVo的属性赋值给course
        BeanUtils.copyProperties(courseInfoVo, course);
        //保存到数据库中
        this.save(course);
        //保存课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionMapper.insert(courseDescription);
        //返回新增的课程id
        return course.getId();
    }

    @Override
    public AdminCourseInfoVo getCourseInfo(String courseId) {
        Course course = this.getById(courseId);
        CourseDescription courseDescription = courseDescriptionMapper.selectById(courseId);
        AdminCourseInfoVo adminCourseInfoVo = new AdminCourseInfoVo();
        BeanUtils.copyProperties(course, adminCourseInfoVo);
        adminCourseInfoVo.setDescription(courseDescription.getDescription());
        return adminCourseInfoVo;
    }

    @Override
    public Page<AdminCourseItemVo> queryCourseItemVoPage(Integer pageNum, Integer pageSize) {
        Page<AdminCourseItemVo> itemVoPage = new Page<>(pageNum, pageSize);
        List<AdminCourseItemVo> data = baseMapper.selectCourseItemVoPage(itemVoPage);
        itemVoPage.setRecords(data);
        return itemVoPage;

    }

    @Override
    public void updateByVoId(AdminCourseInfoVo adminCourseInfoVo, String courseId) {
        Course course = new Course();
        BeanUtils.copyProperties(adminCourseInfoVo,course);
        course.setId(courseId);
        baseMapper.updateById(course);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(adminCourseInfoVo.getDescription());
        courseDescription.setId(courseId);
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public AdminCourseItemVo getPublishInfo(String courseId) {

        return null;
    }
}
