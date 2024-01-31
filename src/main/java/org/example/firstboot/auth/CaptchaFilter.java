package org.example.firstboot.auth;

import org.apache.commons.lang3.StringUtils;
import org.example.firstboot.constant.Constant;
import org.example.firstboot.core.exception.CaptchaException;
import org.example.firstboot.util.RedisUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 验证码过滤器
 * 继承OncePerRequestFilter类，保证该验证码过滤器只会每次请求时执行一次
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    RedisUtils redisUtils;

    @Resource
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();
        if ("/login".equals(url) && request.getMethod().equals("POST")) {
            // 校验验证码
            try {
                validate(request);
            } catch (CaptchaException e) {
                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }

        filterChain.doFilter(request, response);
    }

    // 校验验证码
    private void validate(HttpServletRequest request) throws CaptchaException {

        String code = request.getParameter("code");
        String key = request.getParameter("userKey");

        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisUtils.hget(Constant.CAPTCHA_KEY, key))) {
            throw new CaptchaException("验证码错误");
        }

        // 若验证码正确，从redis删除该验证码
        redisUtils.hdel(Constant.CAPTCHA_KEY, key);
    }
}
