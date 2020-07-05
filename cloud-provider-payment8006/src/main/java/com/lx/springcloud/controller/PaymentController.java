package com.lx.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @RequestMapping(value = "/payment/consul")
    public String getPaymentConsul(){
        return "注册成功";
    }
}
