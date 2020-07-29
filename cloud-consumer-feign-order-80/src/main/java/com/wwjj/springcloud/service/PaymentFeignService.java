package com.wwjj.springcloud.service;

import com.wwjj.springcloud.entites.CommonResult;
import com.wwjj.springcloud.entites.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")  //表示调用哪个微服务   接口加  注解
public interface PaymentFeignService
{
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id);

    @GetMapping("/timeout")
    public String paymentTimeOut();
}
