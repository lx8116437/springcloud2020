package com.lx.springcloud.controller;

import com.lx.springcloud.entities.Result;
import com.lx.springcloud.serices.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "consumer/payment/getPayment/{id}")
    Result getPayment(@PathVariable("id") String id){
        return paymentFeignService.getPayment(id);
    }
}
