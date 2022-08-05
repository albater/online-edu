package com.service.edu.service;

import com.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
public interface SubjectService extends IService<Subject> {
    /**
     * @param subjects
     * @description: 导入课程
     * @return: void
     * @author: alpha
     * @date: 2022/7/26 18:30
     */
    void importSubjects(MultipartFile subjects);
    /**
     * @param
     * @description: 查询嵌套的科目
     * @return: java.util.List<com.service.edu.entity.Subject>
     * @author: alpha
     * @date: 2022/7/26 18:29
     */
    List<Subject> getNestedSubjects();

}
