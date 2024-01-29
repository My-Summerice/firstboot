package org.example.firstboot.auth;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
public class PasswordEncoder extends BCryptPasswordEncoder {

    /**
     * 密码校验
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 接收到的前端的密码
        String pwd = rawPassword.toString();
        // TODO: 正常来讲前端应该要对密码加密，所以这里需要先解密
        //        try {
        //            pwd = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, pwd);
        //        } catch (Exception e) {
        //            throw new BadCredentialsException(e.getMessage());
        //        }

        // 如果解密后的字符串有效则进行后端的加密校验
        if (encodedPassword != null && !encodedPassword.isEmpty()) {
            return BCrypt.checkpw(pwd, encodedPassword);
        } else {
            return false;
        }
    }
}
