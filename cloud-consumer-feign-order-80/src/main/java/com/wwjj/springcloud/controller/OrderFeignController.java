package com.wwjj.springcloud.controller;

import com.wwjj.springcloud.entites.CommonResult;
import com.wwjj.springcloud.entites.Payment;
import com.wwjj.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentByID(@PathVariable("id")long id){
      return   paymentFeignService.getPaymentId(id);
    }

    @GetMapping("/timeout")
    public String payment(){
        return  paymentFeignService.paymentTimeOut();
    }
}
