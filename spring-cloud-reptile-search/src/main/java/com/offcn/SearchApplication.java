package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@SpringBootApplication
@MapperScan(basePackages = "com.offcn.dao")
@EnableDiscoveryClient
public class SearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(SearchApplication.class, args);
    }
}
