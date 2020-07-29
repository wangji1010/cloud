package com.wj.springcloud.handle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wwjj.springcloud.entites.CommonResult;
import com.wwjj.springcloud.entites.Payment;

/*
* 定义全局兜底方法
* */
public class MyHandler
{
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444,":按客户自定义,全局的 兜底 =================1");
    }
    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,":按客户自定义,全局的 兜底 ==============2");
    }
}
