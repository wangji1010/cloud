package com.wwjj.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentHystrixService {
    @Override
    public String payment_ok(Integer id) {
        return "-----------payment_ok 我是实现类来统一的处理了";
    }

    @Override
    public String payment_timeout(Integer id) {
        return "-----------payment_timeout 我是实现类来统一的处理了";
    }
}
