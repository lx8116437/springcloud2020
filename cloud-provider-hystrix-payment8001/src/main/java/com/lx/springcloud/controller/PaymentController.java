package com.lx.springcloud.controller;

import com.lx.springcloud.entities.Result;
import com.lx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hystrix/payment/getPaymentInfo_OK/{id}")
    public Result getPaymentInfo_OK(@PathVariable("id") String id){
        Result r = paymentService.getPaymentInfo_OK(id);
        log.info("端口号:{} 返回值,result:{}",port,r);
        return r;
    }

    @RequestMapping(value = "/hystrix/payment/getPaymentInfo_TimeOut/{id}")
    public Result getPaymentInfo_TimeOut(@PathVariable("id")String id)throws InterruptedException{
        Result r = paymentService.getPaymentInfo_TimeOut(id);
        log.info("端口号:{} 返回值,result:{}",port,r);
        return r;
    }

    @RequestMapping(value = "/hystrix/payment/getPaymentCircuitBreaker/{id}")
    public Result getPaymentCircuitBreaker(@PathVariable("id")int id) throws Exception {
        Result r = paymentService.getPaymentCircuitBreaker(id);
        log.info("端口号:{} 返回值,result:{}",port,r);
        return r;
    }
}
