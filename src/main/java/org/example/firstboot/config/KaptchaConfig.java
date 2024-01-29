package org.example.firstboot.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码配置类
 * 使用的是Google的图片验证码工具类kaptcha
 */
@Configuration
public class KaptchaConfig {

    /**
     * 生成验证码对象
     */
    @Bean
    DefaultKaptcha producer() {

        // 创建一个Properties对象用于集成验证码配置项
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no"); // 边框，no代表无边框
        properties.put("kaptcha.textproducer.font.color", "black"); // 文本字体颜色
        properties.put("kaptcha.textproducer.char.space", "4"); // 文本字符间隔，单位：像素
        properties.put("kaptcha.textproducer.font.size", "30"); // 文本字体大小，单位：像素
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");    // 字体
        properties.put("kaptcha.textproducer.char.string", "0123456789abcdefghijklmnopqrsduvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");   // 可生成的字符范围
        properties.put("kaptcha.textproducer.char.length", "4");    // 生成的字符个数
        properties.put("kaptcha.image.height", "40");   // 图片高度，单位：像素
        properties.put("kaptcha.image.width", "120");   // 图片宽度，单位：像素

        // 将配置的属性塞到Config对象里
        Config config = new Config(properties);

        // 创建一个图片验证码对象并设置配置
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
