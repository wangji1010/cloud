package com.wwjj.springcloud.controller;


import com.wwjj.springcloud.service.PaymentService;
import com.wwjj.springcloud.entites.CommonResult;
import com.wwjj.springcloud.entites.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("*********插入结果" + i);
        if (i > 0) {
            return new CommonResult(200, "插入成功:serverPort:" + serverport, i);
        } else {
            return new CommonResult(444, "插入失败", null);
        }


    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*********插入结果" + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "查询成功:serverPort:" + serverport, paymentById);
        } else {
            return new CommonResult(444, "查询失败", null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("==========:::element::" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("===========:::" + instance.getServiceId() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public String getPaymentLb(){

        return serverport;
    }

    @GetMapping("/timeout")
    public String paymentTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){

        }
        return serverport;
    }

}
