package org.example.firstboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.firstboot.mapper")
public class FirstbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstbootApplication.class, args);
    }
}
