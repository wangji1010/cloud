package com.wwjj.springcloud.controller;

import com.wwjj.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String res = paymentService.PaymentInfo_ok(id);
        return res;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        String res = paymentService.PaymentInfo_timeout(id);
        return res;
    }

    /*服务熔断*/
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuiBreaker(@PathVariable("id") Integer id){
        String res = paymentService.paymentCircuitBreaker(id);

        return res;
    }
}
