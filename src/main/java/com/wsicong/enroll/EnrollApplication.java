package com.wsicong.enroll;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wsicong.enroll.mapper")
public class EnrollApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnrollApplication.class, args);
    }

}
