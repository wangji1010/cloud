package com.wwjj.springcloud;

import com.wwjj.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient//开启表示是客户端将注册到eureka注册中心
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)//给服务配置对应的负载均衡算法
public class OrderMain80
{
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
