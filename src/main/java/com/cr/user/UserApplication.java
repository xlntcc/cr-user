package com.cr.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户中台服务启动类
 */
@SpringBootApplication
@MapperScan("com.cr.user.mapper")
@EnableFeignClients(basePackages = "com.cr.user.api")
public class UserApplication {

    /** 启动入口 */
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
