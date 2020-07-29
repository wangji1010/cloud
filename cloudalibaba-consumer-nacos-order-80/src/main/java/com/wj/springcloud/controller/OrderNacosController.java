package com.wj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosController {

    //服务地址
    @Value("${service-url.nacos-user-service}")
    private String SERVER_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymenInfo(@PathVariable Integer id){

        return restTemplate.getForObject(SERVER_URL+"/payment/nacos/"+id,String.class);
    }

}
