package com.wwjj.springcloud.service;

import com.wwjj.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
