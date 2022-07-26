package com.service.base.exception;

import com.service.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author alpha
 * @className: GlobalException
 * @date 2022/7/23 9:24
 * @Description
 */
@Data
public class GlobalException extends RuntimeException{
    private Integer code;
    //msg可以不设置，因为父类中有
//    public RuntimeException(String message) {
//        super(message);
//    }
    //直接传参数替代就可以了
//    private String msg;
    public GlobalException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
    /**
     * @param codeEnum
     * @description: 调入枚举类设置
     * @return:
     * @author: alpha
     * @date: 2022/7/23 9:47
     */
    public GlobalException(ResultCodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

}
