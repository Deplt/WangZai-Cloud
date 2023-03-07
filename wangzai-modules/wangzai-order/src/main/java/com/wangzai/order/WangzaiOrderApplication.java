package com.wangzai.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WangzaiOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(WangzaiOrderApplication.class, args);
    }
}
