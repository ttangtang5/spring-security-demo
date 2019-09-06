package com.tang.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author tang
 * @Date 2019-08-14 22:23
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.tang.security"})
public class SecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }
}
