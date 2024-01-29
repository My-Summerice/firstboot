//package org.example.firstboot.auth;
//
//import cn.hutool.core.util.StrUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import org.example.firstboot.entity.User;
//import org.example.firstboot.service.UserService;
//import org.example.firstboot.util.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Jwt过滤器
// */
////@Component
//public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @Autowired
//    UserDetailServiceImpl userDetailService;
//
//    @Autowired
//    UserService userService;
//
//    // TODO: bug 报错提示重复定义 AuthenticationManager，该类和配置类冲突
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//
//        super(authenticationManager);
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String jwt = request.getHeader(jwtUtils.getHeader());
//        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
//        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
//        if (StrUtil.isBlankOrUndefined(jwt)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // 解析jwt，获取用户信息
//        Claims claim = jwtUtils.getClaimsByToken(jwt);
//        if (claim == null) {
//            throw new JwtException("token 异常");
//        }
//        if (jwtUtils.isTokenExpired(claim)) {
//            throw new JwtException("token 已过期");
//        }
//
//        // 因为加密的是用户名，所以先解析出来用户名，再根据用户名去查该用户的权限
//        String username = claim.getSubject();
//        User user = userService.getUserByName(username);
//
//        // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, userDetailService.getUserAuthority(user.getId()));
//        SecurityContextHolder.getContext().setAuthentication(token);
//
//        chain.doFilter(request, response);
//    }
//}
