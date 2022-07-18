package com.service.edu.controller.api;


import com.service.base.result.R;
import com.service.edu.entity.Teacher;
import com.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@Api("讲师信息模块")
@RestController
@RequestMapping("/api/edu/teacher")
@Slf4j
public class ApiTeacherController {
    @Autowired
    private TeacherService teacherService;

    //查询所有讲师
    @ApiOperation("查询所有教师信息")
    @GetMapping("/queryAll")
    public R queryAll() {
        List<Teacher> teachers = teacherService.list();
        teachers.forEach(System.out::println);
        return R.ok().data("items", teachers).message("查询所有讲师信息成功");
    }

    @ApiOperation(value = "根据Id删除讲师")
    @DeleteMapping("/deleteById/{id}")
    public R deleteTeacher() {
        //设置日志
        log.debug("当前日志级别 : {} , 当前时间: {}", "debug", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        //开发测试级别使用
        log.info("当前日志级别 : {} , 当前时间: {}", "debug", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        //生产环境使用 警告级别以上
        log.warn("当前日志级别 : {} , 当前时间: {}", "debug", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        //生产环境使用 报错
        log.error("当前日志级别 : {} , 当前时间: {}", "debug", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        boolean result = teacherService.removeById(1);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.ok().message("数据不存在");
        }
    }

    //根据id查询
    @ApiOperation(value = "根据ID查找教师")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }
    @ApiOperation(value = "添加教师")
    @PostMapping("/save")
    public R save(@RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }
}

