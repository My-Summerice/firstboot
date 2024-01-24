package org.example.firstboot.core.result;

import lombok.Getter;

/**
 * 自定义错误码
 */
@Getter
public enum ResultCode {
    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    BAD_REQUEST(400, "请求的数据格式不符!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部异常!"),
    AUTH_ERROR(502, "授权失败!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),
    DATABASE_OPERATION_FAILED(504, "数据库操作失败");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
