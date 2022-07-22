package com.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.edu.entity.Teacher;
import com.service.edu.entity.query.TeacherQuery;
import com.service.edu.mapper.TeacherMapper;
import com.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public void queryPageByCondition(Page<Teacher> page, TeacherQuery teacherQuery) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String intro = teacherQuery.getIntro();
        Date joinDateStart = teacherQuery.getJoinDateStart();
        Date joinDateEnd = teacherQuery.getJoinDateEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like(Teacher::getName, name);
        }
        if (level != null) {
            queryWrapper.eq(Teacher::getLevel,level);
        }
        if (!StringUtils.isEmpty(intro)) {
            queryWrapper.like(Teacher::getIntro,intro);
        }
        if (!StringUtils.isEmpty(joinDateStart)) {
            queryWrapper.eq(Teacher::getJoinDate,joinDateStart);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.eq(Teacher::getJoinDate,joinDateEnd);
        }
        this.page(page, queryWrapper);
    }
}
