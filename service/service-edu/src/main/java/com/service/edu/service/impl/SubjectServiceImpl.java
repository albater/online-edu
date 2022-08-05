package com.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.service.base.exception.GlobalException;
import com.service.base.result.ResultCodeEnum;
import com.service.edu.entity.Subject;
import com.service.edu.entity.excel.SubjectExcelData;
import com.service.edu.listener.SubjectExcelDataListener;
import com.service.edu.mapper.SubjectMapper;
import com.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@Service
@Slf4j
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void importSubjects(MultipartFile subjects) {
        try {
            EasyExcel.read(subjects.getInputStream())
                    .head(SubjectExcelData.class)
                    .registerReadListener(new SubjectExcelDataListener(baseMapper))
                    .doReadAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new GlobalException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @Override
    public List<Subject> getNestedSubjects() {

        List<Subject> subjects = this.list();
        //查出一级分类
        List<Subject> pSubjects = subjects.stream()
                .filter(subject ->
                        subject.getParentId().equals("0"))
                .collect(Collectors.toList());
        //根据一级分类查询二级分类
        pSubjects.forEach(pSubject -> {
            List<Subject> cSubjects = subjects.stream()
                    .filter(subject -> subject.getParentId().equals(pSubject.getId()))
                    .collect(Collectors.toList());
            //把查询到的二级分类设置给一级分类的子分类
            pSubject.setChildren(cSubjects);
        });
        return pSubjects;
    }
}
