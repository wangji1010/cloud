package com.wwjj.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wwjj.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "PaymentInfo_timeout_globle") //全局兜底的方法
public class OrderHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        return paymentHystrixService.payment_ok(id);
    }
   /* @HystrixCommand(fallbackMethod = "PaymentInfo_timeout_handler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })*/
   @HystrixCommand //不指定则会使用全局兜底的方法
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
//        int age = id/0;
        return paymentHystrixService.payment_timeout(id);
    }

    public String PaymentInfo_timeout_handler(@PathVariable("id") Integer id){
        return "精确的服务降级方法";
    }

    public String PaymentInfo_timeout_globle(){
        return "全局的服务降级方法";
    }
}
