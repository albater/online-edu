package com.service.edu.controller.admin;

import com.service.base.result.R;
import com.service.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author alpha
 * @className: AdminSubjectController
 * @date 2022/7/26 0:31
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/edu/subject")
public class AdminSubjectController {

    @Autowired
    SubjectService subjectService;


    @PostMapping("import")
    public R importSubjects(MultipartFile subjects){
        subjectService.importSubjects(subjects);
        return R.ok();
    }
}
