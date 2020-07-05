package com.lx.springcloud.controller;

import com.lx.springcloud.config.ApplicationContextConfig;
import com.lx.springcloud.entities.Payment;
import com.lx.springcloud.entities.Result;
import com.lx.springcloud.lb.LoadBalanced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    private static final String PAYMENT_URL = "http://localhost:8001"; 单机版
    //集群版
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalanced loadBalanced;

    @GetMapping(value = "/consumer/payment/insertPayment")
    public Result insertPayment(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/insertPayment", payment, Result.class);
    }

    @GetMapping(value = "/consumer/payment/getPayment/{id}")
    public Result getPayment(@PathVariable("id") String id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPayment/" + id,Result.class);
    }

    @GetMapping(value = "/consumer/getPayment/getPayment1/{id}")
    public Result getPayment1(@PathVariable("id") String id){
        ResponseEntity<Result> r = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPayment/" + id,Result.class);
        log.info(r.getStatusCode() + "\t" + r.getHeaders());
        return r.getBody();
    }

    //负载均衡轮询算法:rest接口第几次请求数 %(取余) 服务器集群数量 = 实际调用服务器位置下标,每次服务器器重启后rest接口计数从1开始
    @GetMapping(value = "/consumer/getPayment/getPaymentLB")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance instance = loadBalanced.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/getLb", String.class);
    }

}
