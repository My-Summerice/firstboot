package org.example.firstboot.service.impl;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import org.example.firstboot.constant.Constant;
import org.example.firstboot.service.AuthService;
import org.example.firstboot.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private Producer producer;

    @Autowired   // 因为工具类中的redis对象是使用注解自动装配的，所以在这里直接new的话只能拿到一个空的redis对象
    private RedisUtils redisUtils;

    /**
     * 生成图形验证码
     */
    @Override
    public Map image() throws IOException {
        // 生成一个随机数作为验证码的key
        String key = UUID.randomUUID().toString();
        // 根据设定好的配置生成字符串验证码
        String code = producer.createText();

        // 根据字符串验证码生成图片验证码
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        String str = "data:image/jpeg;base64,";

        String base64Img = str + Base64Encoder.encode(outputStream.toByteArray());

        // 将随机数与验证码写入redis
        redisUtils.hset(Constant.CAPTCHA_KEY, key, base64Img, 120);

        return MapUtil.builder().put("userKey", key).put("captcherImg", base64Img).build();
    }

    /**
     * 生成字符串验证码
     */
    @Override
    public Map text() {
        // 生成一个随机数作为验证码的key
        String key = UUID.randomUUID().toString();
        // 根据设定好的配置生成字符串验证码
        String code = producer.createText();

        // 将随机数和验证码写入redis
        redisUtils.hset(Constant.CAPTCHA_KEY, key, code, 120);

        return MapUtil.builder().put("userKey", key).put("captcherText", code).build();
    }
}
