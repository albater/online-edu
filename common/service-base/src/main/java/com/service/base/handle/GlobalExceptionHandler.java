package com.service.base.handle;

import com.service.base.result.R;
import com.service.base.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author alpha
 * @className: GlobalExceptionHandler
 * @date 2022/7/18 2:07
 * @Description
 */
@RestControllerAdvice //异常处理时 起作用 返回值为json格式
@Slf4j
public class GlobalExceptionHandler {
    //设置最大异常处理器
    @ExceptionHandler(value = Exception.class)
    public R exception(Exception e){
        //将异常信息转为字符串
        log.error("{}",ExceptionUtils.getStackTrace(e));
        return R.error();
    }
    //算数异常处理器
    @ExceptionHandler(value = ArithmeticException.class)
    public R exception(ArithmeticException e){
        //捕获异常输出
        log.error("{}", ExceptionUtils.getStackTrace(e));
        return R.setResult(ResultCodeEnum.DIV_ZERO_ERROR);
    }
}
