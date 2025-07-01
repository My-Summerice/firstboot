package org.example.firstboot;

import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StopWatch;

import static java.lang.Thread.sleep;

@PropertySource(value = "classpath:application.properties")
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FirstbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        redisTemplate.opsForValue().set("name", "张三");

    }

    @Test
    void testStopWatch() throws InterruptedException {

        StopWatch sw = new StopWatch();
        sw.start("开始打印");
        sleep(1000);
        System.out.println(redisTemplate.opsForValue().get("name"));
        sleep(1000);
        sw.stop();
        System.out.println(sw.shortSummary());
    }

}
