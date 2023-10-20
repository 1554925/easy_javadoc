package com.example.cloud.project.integrated.translate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.cloud.project.integrated.translate.dao.mapper")
public class IntegratedTranslateApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegratedTranslateApplication.class, args);
    }

}
