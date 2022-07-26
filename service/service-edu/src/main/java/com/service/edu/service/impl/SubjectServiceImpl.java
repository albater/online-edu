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
}
