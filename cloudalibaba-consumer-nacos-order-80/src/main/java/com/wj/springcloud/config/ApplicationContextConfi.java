package com.wj.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfi {
    @Bean
    @LoadBalanced //开启负载均衡（轮询） **
    public RestTemplate getRestTenplate(){

        return new RestTemplate();
    }
}

