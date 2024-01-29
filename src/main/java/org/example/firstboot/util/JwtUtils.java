package org.example.firstboot.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Jwt工具类
 * 主要用于生成token、解析token、判断token是否过期
 */
@Data
@Component
// 当我们的类不属于spring的各种归类（不属于@Controller/@Service/@Repository）的时候，而我们又需要使用spring的其他注解（比如下面这个读取配置文件的注解）的时候，就需要加上该注解，效果为将该类加入spring的容器管理中
@ConfigurationProperties(prefix = "auth.jwt")   // 获取生效的配置文件中的指定参数
public class JwtUtils {

    private String header;
    private String secret;
    private long   expire;

    // 注册生成jwt
    public String generateToken(String username) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire); // 配置文件中expire的单位是秒，在这里需转化成毫秒

        return Jwts.builder()   // 创建一个JWT Builder对象，用于构建JWT
                .setHeaderParam("typ", "JWT")   // 设置请求头的"typ"参数为"JWT"，"typ"代表令牌类型
                .setSubject(username)   // 指定被加密的字符串
                .setIssuedAt(nowDate)   // 设置JWT的创建时间
                .setExpiration(expireDate)  // 设置JWT的过期时间
                .signWith(SignatureAlgorithm.HS512, secret) // 指定加密算法和密钥
                .compact(); // 返回构建出的JWT的字符串形式
    }

    // 解析jwt
    public Claims getClaimsByToken(String jwt) {

        return Jwts.parser()    // 创建一个JWT Parser对象，用于解析JWT
                .setSigningKey(secret)  // 指定密钥，需要注意的是：解密的时候不需要指定加密算法，算法类型已经在加密的时候放进了jwt的header的"alg"字段
                .parseClaimsJws(jwt)    // 指定待解析的字符串
                .getBody(); // 如果解析成功，该方法就可以获取JWT中的声明（Claims），包含被加密对象和过期时间等信息
    }

    // 校验jwt是否过期
    public boolean isTokenExpired(Claims claims) {

        return claims.getExpiration().before(new Date());   // 判断过期时间是否小于当前时间
    }
}
