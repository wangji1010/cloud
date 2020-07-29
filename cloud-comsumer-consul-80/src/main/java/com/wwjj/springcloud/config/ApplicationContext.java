package com.wwjj.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContext {
    @Bean
    @LoadBalanced//赋予 RestTemplate 负载均衡机制，（多个服务必须加上）
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
