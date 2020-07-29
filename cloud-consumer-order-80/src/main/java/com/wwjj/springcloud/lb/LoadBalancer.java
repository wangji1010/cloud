package com.wwjj.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer
{
    ServiceInstance instance(List<ServiceInstance> instances );//得到活着的服务
}
