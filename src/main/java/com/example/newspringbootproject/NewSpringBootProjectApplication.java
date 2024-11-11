package com.example.newspringbootproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.newspringbootproject.mapper")
public class NewSpringBootProjectApplication {
    public static void main(String[] args){
        SpringApplication.run(NewSpringBootProjectApplication.class);
    }
}
