package com.superstudentregion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan({"com.superstudentregion.mapper"})
public class SuperstudentregionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperstudentregionApplication.class, args);
    }

}
