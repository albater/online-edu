package com.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.base.result.R;
import com.service.edu.entity.Course;
import com.service.edu.entity.vo.AdminCourseInfoVo;
import com.service.edu.entity.vo.AdminCourseItemVo;
import com.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@RestController
@CrossOrigin
@Api("课程展示模块")
@RequestMapping("/admin/edu/course")
public class AdminCourseController {
    @Autowired
    CourseService courseService;

    //7.查询指定日期发布课程的数量
    @GetMapping("getCoursePublishNum/{day}")
    public R getCoursePublishNum(@PathVariable("day") String day) {
        long count = courseService.count(new QueryWrapper<Course>()
                .eq("date(publish_time)", day));
        return R.ok().data("count", count);
    }

    //6.发布课程
    @ApiOperation(value = "发布课程")
    @PutMapping("/publish/{courseId}")
    public R publish(@PathVariable("courseId") String courseId) {
        courseService.update(new LambdaUpdateWrapper<Course>()
                .set(Course::getStatus, "Normal")
                .set(Course::getPublishTime, new Date())
                .eq(Course::getId, courseId));
        return R.ok();
    }

    //5.查询课程的发布信息
    @ApiOperation(value = "查询课程的发布信息")
    @GetMapping("/getPublishInfo/{courseId}")
    public R getPublishInfo(@PathVariable("courseId") String courseId) {
        AdminCourseItemVo vo = courseService.getPublishInfo(courseId);
        return R.ok().data("item", vo);
    }

    //4.根据id更新课程
    @ApiOperation(value = "根据id更新课程")
    @PutMapping("/updateById/{courseId}")
    public R updateById(@RequestBody AdminCourseInfoVo adminCourseInfoVo,
                        @PathVariable("courseId") String courseId) {
        courseService.updateByVoId(adminCourseInfoVo, courseId);
        return R.ok();
    }

    //3.分页显示数据
    @ApiOperation(value = "分页显示数据")
    @GetMapping("/queryPage/{pageNum}/{pageSize}")
    public R queryPage(@PathVariable("pageNum") Integer pageNum,
                       @PathVariable("pageSize") Integer pageSize) {
        Page<AdminCourseItemVo> page = courseService.queryCourseItemVoPage(pageNum, pageSize);
        return R.ok().data("page", page);
    }

    //2.保存课程详情
    @ApiOperation(value = "保存课程详情")
    @PostMapping("/saveCourseInfo")
    public R saveCourseInfo(@RequestBody AdminCourseInfoVo adminCourseInfoVo) {
        String courseId = courseService.saveCourseInfo(adminCourseInfoVo);
        return R.ok().data("id", courseId);
    }

    //1.获取课程详情
    @ApiOperation(value = "获取课程详情")
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable("courseId") String courseId) {
        AdminCourseInfoVo vo = courseService.getCourseInfo(courseId);
        return R.ok().data("item", vo);
    }
}

