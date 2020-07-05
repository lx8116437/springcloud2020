package com.lx.springcloud.controller;

import com.lx.springcloud.entities.Payment;
import com.lx.springcloud.entities.Result;
import com.lx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/getPayment/{id}")
    private Result getPayment(@PathVariable("id") String id) {
        log.info("端口号:" + serverPort);
        return paymentService.getPaymentById(id);
    }

    /**
     *新增
     * @param payment
     * @return
     */
    @PostMapping(value = "/payment/insertPayment")
    private Result insertPayment(@RequestBody Payment payment) {
        log.info("端口号:" + serverPort);
        return paymentService.insertPayment(payment);
    }

    /**
     * 服务发现Discovery
     * @return
     */
    @GetMapping(value = "/payment/getDiscovery")
    public Object getDiscovery() {
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            log.info("*****遍历services:" + s);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/getLb")
    public String getLb(){
        return serverPort;
    }

}
