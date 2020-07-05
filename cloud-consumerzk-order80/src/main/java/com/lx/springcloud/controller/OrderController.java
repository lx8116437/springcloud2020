package com.lx.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    private final static String ADDRESS = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumerzk/getPayment")
    public String getPayment(){
        return restTemplate.getForObject(ADDRESS + "/payment/zk", String.class);
    }
}
