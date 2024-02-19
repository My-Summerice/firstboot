package org.example.firstboot.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.firstboot.core.result.GlobalResult;
import org.example.firstboot.core.result.ResultCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理业务自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public GlobalResult<String> processCustomException(CustomException e) {
        return GlobalResult.error(e.getErrCode(), e.getErrMsg());
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public GlobalResult<String> processNullPointerException(NullPointerException e) {
        log.error(e.getMessage());
        return GlobalResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GlobalResult<String> processValidationException() {
        return GlobalResult.error(ResultCode.BAD_REQUEST);
    }

    /**
     * 处理验证码校验异常
     */
    @ExceptionHandler(CaptchaException.class)
    public GlobalResult<String> processCaptchaException(CaptchaException e) {

        return GlobalResult.error(e.getMessage());
    }

    // 这个没有意义，因为只有直接或间接的继承了 RuntimeException 运行时异常的时候才会被全局异常捕获捕获到
    // 而 Exception 是 RuntimeException 的父类，所以程序在编译阶段无法确认该 Exception 是否为运行时异常而报错。
    //    /**
    //     * 处理其它异常
    //     */
    //    @ExceptionHandler(Exception.class)
    //    public GlobalResult<String> processOtherException(Exception e) {
    //        log.error(e.getMessage());
    //        return GlobalResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    //    }
}
