package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@SpringBootApplication
@EnableEurekaServer
public class RegEurekaApplication {
    public static void main(String[] args) {

        SpringApplication.run(RegEurekaApplication.class, args);
    }
}
