package com.wj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wj.springcloud.handle.MyHandler;
import com.wwjj.springcloud.entites.CommonResult;
import com.wwjj.springcloud.entites.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    /*热点限流*/

    /*唯一名字，
     * 如果违背了在web界面配置的规则，
     * 则执行兜底方法*/

    /*
    @SentinelResource
    * 参数一：资源名，唯一标识，在web界面中用于配置热点限流规则
    * 参数二：blockHandler 配置兜底的方法，当违背了热点限流规则触发
    * */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult testHotKey(){

        //如果是Sentinel控制台配置违规情况，则会有blockHandler 配置方法进行兜底
        //【注意】 但是如果代码自身异常则不会进行兜底
//        int a = 10/0;
        return new CommonResult(200,"按照资源名称限流ok",new Payment(2020L,"serial2000"));
    }
    public CommonResult handleException (BlockException exception){
        return new CommonResult(400,exception.getClass().getCanonicalName()+"服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){

        //如果是Sentinel控制台配置违规情况，则会有blockHandler 配置方法进行兜底
        //【注意】 但是如果代码自身异常则不会进行兜底
//        int a = 10/0;
        return new CommonResult(200,"byUrl:按照资源名称限流ok",new Payment(2020L,"serial2000"));
    }
//=========================================CustomerBlockHandler
    @GetMapping("/byUrl/CustomerBlockHandler")
    @SentinelResource(value = "CustomerBlockHandler"
            ,blockHandlerClass = MyHandler.class,blockHandler = "handlerException2")
    public CommonResult CustomerBlockHandler(){

        //如果是Sentinel控制台配置违规情况，则会有blockHandler 配置方法进行兜底
        //【注意】 但是如果代码自身异常则不会进行兜底
//        int a = 10/0;
        return new CommonResult(200,":按客户自定义",new Payment(2020L,"serial2000"));
    }

}
