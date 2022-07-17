package com.service.edu.controller;


import com.service.edu.entity.Teacher;
import com.service.edu.mapper.TeacherMapper;
import com.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //查询所有讲师
    @GetMapping("/queryAll")
    public List<Teacher> queryAll(){
        List<Teacher> teachers = teacherService.list();
        teachers.forEach(System.out::println);
        return teachers;
    }

    @GetMapping
    public boolean deleteTeacher(){
        boolean b = teacherService.removeById(1);
        return  b;
    }

}

