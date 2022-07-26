package com.service.edu.service;

import com.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
public interface SubjectService extends IService<Subject> {

    void importSubjects(MultipartFile subjects);
}
