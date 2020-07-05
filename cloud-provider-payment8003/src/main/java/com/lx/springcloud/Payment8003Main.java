package com.lx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Payment8003Main {
    public static void main(String[] args) {
        SpringApplication.run(Payment8003Main.class, args);
    }
}
