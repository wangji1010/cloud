package springcloud.controller;


import com.wwjj.springcloud.entites.CommonResult;
import com.wwjj.springcloud.entites.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springcloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("*********插入结果"+i);
        if (i>0){
            return new CommonResult(200,"插入成功:serverPort::"+serverport,i);
        }else {
            return new CommonResult(444,"插入失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id")Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*********插入结果"+paymentById);
        if (paymentById!=null){
            return new CommonResult(200,"查询成功:serverPort::"+serverport,paymentById);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }
    @GetMapping("/payment/lb")
    public String getPaymentLb(){

        return serverport;
    }

}
