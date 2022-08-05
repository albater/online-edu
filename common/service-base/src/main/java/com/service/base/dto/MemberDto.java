package com.service.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alpha
 * @className: MemberDto
 * @date 2022/8/3 20:16
 * @Description
 */
@Data
public class MemberDto {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "id")//swagger 生成的文档中隐藏该属性
    private String id;
}
