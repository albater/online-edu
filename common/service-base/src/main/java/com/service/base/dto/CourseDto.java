package com.service.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author alpha
 * @className: CourseDto
 * @date 2022/8/3 20:14
 * @Description
 */
@Data
public class CourseDto {
    @ApiModelProperty(value = "课程讲师姓名")
    private String teacherName;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "id")//swagger 生成的文档中隐藏该属性
    private String id;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
}
