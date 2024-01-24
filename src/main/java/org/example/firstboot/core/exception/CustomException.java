package org.example.firstboot.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.example.firstboot.core.result.ResultCode;

/**
 * 自定义异常类
 */
@Setter
@Getter
public class CustomException extends RuntimeException {
    protected int errCode;

    protected String errMsg;

    public CustomException() {
        super();
    }

    /**
     * 手动输入错误信息
     */
    public CustomException(String errMsg) {
        this.errCode = -1;  // 不传默认-1
        this.errMsg = errMsg;
    }

    /**
     * 手动输入错误码和错误信息
     */
    public CustomException(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * 解析自定义错误码
     */
    public CustomException(ResultCode resultCode) {
        this.errCode = resultCode.getCode();
        this.errMsg = resultCode.getMessage();
    }
}
