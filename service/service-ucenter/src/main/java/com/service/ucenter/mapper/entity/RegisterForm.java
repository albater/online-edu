package com.service.ucenter.mapper.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alpha
 * @className: RegisterForm
 * @date 2022/8/1 20:57
 * @Description
 */
@Data
@ApiModel(value = "用户注册对象")
public class RegisterForm {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "验证码")
    private String code;
}
