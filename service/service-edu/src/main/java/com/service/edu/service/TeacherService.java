package com.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.edu.entity.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
public interface TeacherService extends IService<Teacher> {

    void queryPageByCondition(Page<Teacher> page, TeacherQuery teacherQuery);
}
