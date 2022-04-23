package com.yann.manager;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.yann.manager.mapper")
public class SpringbootSecurityApplication {

    public static void main(String[] args) {
      SpringApplication.run(SpringbootSecurityApplication.class, args);
    }

}
