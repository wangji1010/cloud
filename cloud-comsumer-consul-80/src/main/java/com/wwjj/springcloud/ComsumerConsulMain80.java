package com.wwjj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ComsumerConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ComsumerConsulMain80.class,args);
    }
}
