package com.lx.springcloud.lb.impl;

import com.lx.springcloud.lb.LoadBalanced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负载均衡轮训算法
 */
@Component
@Slf4j
public class MyLB implements LoadBalanced {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));//自旋锁
        log.info("********第几次访问,次数next={}", next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instanceList) {

        int index = getAndIncrement() % instanceList.size();

        return instanceList.get(index);
    }
}
