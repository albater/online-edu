package com.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.edu.entity.Subject;
import com.service.edu.entity.excel.SubjectExcelData;
import com.service.edu.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author alpha
 * @className: SubjectExcelDataListener
 * @date 2022/7/26 0:22
 * @Description
 */
@Component
public class SubjectExcelDataListener extends AnalysisEventListener<SubjectExcelData> {
//    @Autowired
    SubjectMapper subjectMapper;
    public SubjectExcelDataListener(SubjectMapper baseMapper) {
        this.subjectMapper = baseMapper;
    }
    @Override
    public void invoke(SubjectExcelData subjectExcelData, AnalysisContext analysisContext) {
        String levelTwoSubject = subjectExcelData.getLevelTwoSubject();
        String levelOneSubject = subjectExcelData.getLevelOneSubject();

        LambdaQueryWrapper<Subject> query = new LambdaQueryWrapper<>();
        query.eq(Subject::getTitle,levelOneSubject);
        query.eq(Subject::getParentId,"0");
        Subject parentSubject = subjectMapper.selectOne(query);

        if(parentSubject==null){//数据库中不存在 一级分类 需要新增
            parentSubject = new Subject();
            parentSubject.setTitle(levelOneSubject);
            parentSubject.setSort(0);
            parentSubject.setParentId("0");
            subjectMapper.insert(parentSubject);//存入成功会自动返回一级分类的id设置给parentSubject
        }
        String subjectId = parentSubject.getId();//获取一级分类的id
        //3、如果二级分类不存在  新增(一定要绑定它的父分类的id)
        query.clear();//清空之前缓存的条件
        query.eq(Subject::getTitle , levelTwoSubject);
        query.eq(Subject::getParentId , subjectId);//查询上面的一级分类的二级分类对象
        Long count = subjectMapper.selectCount(query);
        if(count==0){//查询不到二级分类
            Subject subject = new Subject();
            subject.setTitle(levelTwoSubject);
            subject.setParentId(subjectId);
            subject.setSort(0);
            subjectMapper.insert(subject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
