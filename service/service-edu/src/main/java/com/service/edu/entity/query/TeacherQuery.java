package com.service.edu.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author alpha
 * @className: TeacherQuery
 * @date 2022/7/20 15:29
 * @Description
 */
@Data
@ApiModel(value = "封装暴露到前端的显示数据")
public class TeacherQuery {
    @ApiModelProperty(value = "讲师姓名")
    private String name;
    @ApiModelProperty(value = "讲师简介")
    private String intro;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "起始日期")
    private Date joinDateStart;
    @ApiModelProperty(value = "截止日期")
    private Date joinDateEnd;
}
