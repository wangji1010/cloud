package com.wwjj.springcloud.controller;

import com.wwjj.springcloud.entites.CommonResult;
import com.wwjj.springcloud.entites.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverport;

    @RequestMapping("/payment/zk")
    public String paymentzk(){
        return "springcloud-with zookeeper::"+serverport+"\t"+ UUID.randomUUID().toString();
    }
}
