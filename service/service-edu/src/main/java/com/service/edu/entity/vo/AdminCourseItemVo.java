package com.service.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author alpha
 * @className: AdminCourseItemVo
 * @date 2022/7/27 0:39
 * @Description
 */
@ApiModel(value = "课程列表详情")
@Data
public class AdminCourseItemVo {
    @ApiModelProperty(value = "id" , hidden = true)//swagger 生成的文档中隐藏该属性
    private String id;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "销售数量")
    private Long buyCount;
    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "课程专业父级标题")
    private String subjectParentTitle;
    @ApiModelProperty(value = "课程专业标题")
    private String subjectTitle;
    @ApiModelProperty(value = "课程讲师姓名")
    private String teacherName;

}
