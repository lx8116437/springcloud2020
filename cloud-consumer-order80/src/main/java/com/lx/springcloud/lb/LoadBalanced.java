package com.lx.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {
    ServiceInstance instance(List<ServiceInstance> instanceList);
}
