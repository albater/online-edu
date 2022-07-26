package com.service.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author alpha
 * @className: SubjectExcelData
 * @date 2022/7/26 0:17
 * @Description
 */
@Data
public class SubjectExcelData {
//    @ExcelProperty(value = "编号")
//    private String id;
    @ExcelProperty(value = "一级目录",index = 0)
    private String levelOneSubject;
    @ExcelProperty(value = "二级目录",index = 1)
    private String levelTwoSubject;
}
