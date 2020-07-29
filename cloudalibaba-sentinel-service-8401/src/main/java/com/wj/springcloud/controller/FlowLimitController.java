package com.wj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        return "-------testA";
    }
    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"=====testB");
        return "-------testB";
    }

    @GetMapping("/testD")
    public String testD() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
////        log.info(Thread.currentThread().getName()+"=====testD");
        log.info("testD 异常比例测试");
        int age = 10/0;
        return "-------testD";
    }
    @GetMapping("/testE")
    public String testE() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
////        log.info(Thread.currentThread().getName()+"=====testD");
        log.info("testE 异常数测试");
        int age = 10/0;
        return "-------testE";
    }

    /*热点限流*/
    @GetMapping("/testHotKey")
    /*唯一名字，
    * 如果违背了在web界面配置的规则，
    * 则执行兜底方法*/

    /*
    @SentinelResource
    * 参数一：资源名，唯一标识，在web界面中用于配置热点限流规则
    * 参数二：blockHandler 配置兜底的方法，当违背了热点限流规则触发
    * */
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){

        //如果是Sentinel控制台配置违规情况，则会有blockHandler 配置方法进行兜底
        //【注意】 但是如果代码自身异常则不会进行兜底
//        int a = 10/0;
        return "======testHotKey====";
    }

    public String deal_testHotKey (String p1, String p2, BlockException exception){

        return "=====deal_testHotKey======兜底了";
    }
}
