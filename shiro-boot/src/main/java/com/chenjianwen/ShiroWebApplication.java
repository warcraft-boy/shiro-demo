package com.chenjianwen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.chenjianwen.mapper"})
@ComponentScan
public class ShiroWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroWebApplication.class, args);
    }

}
