package com.wwjj.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /*
    * 正常访问
    * */
    public String PaymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+" : PaymentInfo_ok"+id;
    }


    @HystrixCommand(fallbackMethod = "PaymentInfo_timeout_handler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String PaymentInfo_timeout(Integer id){
        int time=2;
//        int age =10/0;
        try {
            TimeUnit.SECONDS.sleep(time);
        }catch (Exception e){
            System.out.println(e);
        }
        return "线程池："+Thread.currentThread().getName()+" : PaymentInfo_timeout"+id+"耗时3s";
    }
    //服务降级
    public String PaymentInfo_timeout_handler(Integer id){

        return "我来兜底了"+Thread.currentThread().getName()+"(*^_^*)";
    }

    /*服务熔断*/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
