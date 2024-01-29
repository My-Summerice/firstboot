package org.example.firstboot.service;

import java.io.IOException;
import java.util.Map;

public interface AuthService {

    // 获取图片验证码
    Map image() throws IOException;

    // 获取字符串验证码
    Map text();

}
