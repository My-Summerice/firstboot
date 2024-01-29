package org.example.firstboot.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String errMsg) {

        super(errMsg);
    }
}
