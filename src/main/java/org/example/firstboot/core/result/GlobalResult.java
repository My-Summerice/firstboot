package org.example.firstboot.core.result;

import lombok.Data;

/**
 * 统一接口回参格式
 * @param <T>
 */
@Data
public class GlobalResult<T> {
    private int code;
    private String message;
    private T data;

    public GlobalResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     */
    public static <T> GlobalResult<T> success(T data) {
        return new GlobalResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> GlobalResult<T> success() {
        return new GlobalResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 失败
     */
    public static <T> GlobalResult<T> error(int code, String message) {
        return new GlobalResult<T>(code, message);
    }

    /**
     * 失败
     */
    public static <T> GlobalResult<T> error(String message) {
        return new GlobalResult<T>(-1, message);
    }

    /**
     * 失败
     */
    public static <T> GlobalResult<T> error(ResultCode resultCode) {
        return new GlobalResult<T>(resultCode.getCode(), resultCode.getMessage());
    }
}
