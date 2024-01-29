package org.example.firstboot.auth;

import cn.hutool.json.JSONUtil;
import org.example.firstboot.core.exception.CaptchaException;
import org.example.firstboot.core.result.GlobalResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 登录失败处理方法
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        // 处理错误信息
        String errorMessage = "用户名或密码错误";

        // 验证码校验异常类
        if (e instanceof CaptchaException) {
            errorMessage = "验证码错误";
        }

        // 默认失败
        GlobalResult<Object> result = GlobalResult.error(errorMessage);

        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
