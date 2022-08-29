package com.service.edu.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.base.dto.CourseDto;
import com.service.base.result.R;
import com.service.edu.entity.Course;
import com.service.edu.entity.Teacher;
import com.service.edu.service.CourseService;
import com.service.edu.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author alpha
 * @className: ApiCourseController
 * @date 2022/7/30 9:04
 * @Description
 * TODO:课程查询
 */
@CrossOrigin
@RequestMapping("/api/edu/course")
public class ApiCourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    //5.订单查询订单的课程详情
    @GetMapping("getCourseDto/{id}")
    public R getCourseDto(@PathVariable("id")String id){
        Course course = courseService.getById(id);
        //todo
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(course,courseDto);
        Teacher teacher = teacherService.getById(course.getTeacherId());
        courseDto.setTeacherName(teacher.getName());
        return R.ok().data("item",courseDto);
    }

    //4、查询热门的8个课程的数据
    @GetMapping("getHotCourses")
    public R getHotCourses(){
        List<Course> courses = courseService.list(new LambdaQueryWrapper<Course>()
                .select(Course::getId,Course::getTitle,Course::getCover,Course::getPrice,Course::getViewCount,
                        Course::getBuyCount)
                .orderByDesc(Course::getViewCount)
                .last("limit 8"));
        return R.ok().data("items",courses);
    }

//    //3、根据id查询课程详情的接口
//    @GetMapping("{id}")
//    public R getCourseDetailVo(@PathVariable("id") String id){
//        AdminCourseInfoVo courseDetailVo = courseService.AdminCourseInfoVo(id);
//        return R.ok().data("item",courseDetailVo);
//    }
//    //2、带条件查询课程列表的接口
//    @GetMapping
//    public R list(ApiCourseQuery courseQuery){
//        List<Course> list = courseService.queryCoursesByCondition(courseQuery);
//        return R.ok().data("items" , list);
//    }
    //1、根据讲师id查询课程列表
    @GetMapping("queryCoursesByTeacherId/{teacherId}")
    public R queryCoursesByTeacherId(@PathVariable("teacherId")String teacherId){
        List<Course> list = courseService.list(new LambdaQueryWrapper<Course>()
                .eq(Course::getTeacherId, teacherId));
        return R.ok().data("items",list);
    }

}
