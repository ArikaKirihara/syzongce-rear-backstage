package com.sxy.zongce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//启动类
@SpringBootApplication
@MapperScan("com.sxy.zongce.mapper")
public class ZongceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZongceApplication.class, args);
    }
}
