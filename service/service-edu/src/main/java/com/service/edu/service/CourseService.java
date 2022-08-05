package com.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.edu.entity.vo.AdminCourseInfoVo;
import com.service.edu.entity.vo.AdminCourseItemVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
public interface CourseService extends IService<Course> {
    /**
     * @param courseInfoVo
     * @description: 保存课程详情
     * @return: java.lang.String
     * @author: alpha
     * @date: 2022/7/26 22:33
     */
    String saveCourseInfo(AdminCourseInfoVo courseInfoVo);
    /**
     * @param courseId
     * @description: 根据课程id查询课程详情
     * @return: com.service.edu.entity.vo.AdminCourseInfoVo
     * @author: alpha
     * @date: 2022/7/26 22:57
     */
    AdminCourseInfoVo getCourseInfo(String courseId);
    /**
     * @param pageNum
 *      @param pageSize
     * @description: 分页查询
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.service.edu.entity.vo.AdminCourseItemVo>
     * @author: alpha
     * @date: 2022/7/27 0:42
     */
    Page<AdminCourseItemVo> queryCourseItemVoPage(Integer pageNum, Integer pageSize);
    /**
     * @param adminCourseInfoVo
     * @param courseId
     * @description: 根据id更新课程
     * @return: void
     * @author: alpha
     * @date: 2022/7/27 10:02
     */
    void updateByVoId(AdminCourseInfoVo adminCourseInfoVo, String courseId);
    /**
     * @param courseId
     * @description: 根据id查询发布课程详情信息
     * @return: com.service.edu.entity.vo.AdminCourseItemVo
     * @author: alpha
     * @date: 2022/7/27 18:48
     */
    AdminCourseItemVo getPublishInfo(String courseId);

}
