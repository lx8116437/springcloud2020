package com.lx.springcloud.controller;

import com.lx.springcloud.entities.Payment;
import com.lx.springcloud.entities.Result;
import com.lx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @GetMapping(value = "/payment/getPayment/{id}")
    private Result getPayment(@PathVariable("id") String id) {
        log.info("端口号:" + serverPort);
        return paymentService.getPaymentById(id);
    }

    @PostMapping(value = "/payment/insertPayment")
    private Result insertPayment(@RequestBody Payment payment) {
        log.info("端口号:" + serverPort);
        return paymentService.insertPayment(payment);
    }

    @GetMapping(value = "/payment/getLb")
    public String getLb(){
        return serverPort;
    }
}
