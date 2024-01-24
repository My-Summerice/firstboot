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
    public GlobalResult<String> processValidationException(MethodArgumentNotValidException e) {
        return GlobalResult.error(ResultCode.BAD_REQUEST);
    }

    /**
     * 处理其它异常
     */
    @ExceptionHandler(Exception.class)
    public GlobalResult<String> processOtherException(Exception e) {
        log.error(e.getMessage());
        return GlobalResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
