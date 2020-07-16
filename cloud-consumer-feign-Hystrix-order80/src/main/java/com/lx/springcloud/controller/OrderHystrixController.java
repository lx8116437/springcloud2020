package com.lx.springcloud.controller;

import com.lx.springcloud.entities.Result;
import com.lx.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    PaymentService paymentService;

    @RequestMapping(value = "/consumer/hystrix/payment/getPaymentInfo_OK/{id}")
    public Result getPaymentInfo_OK(@PathVariable("id") String id){
        Result r = paymentService.getPaymentInfo_OK(id);
        return r;
    }

    @RequestMapping(value = "/consumer/hystrix/payment/getPaymentInfo_TimeOut/{id}")
//    @HystrixCommand(fallbackMethod = "getPaymentInfo_TimeOutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })
    @HystrixCommand
    public Result getPaymentInfo_TimeOut(@PathVariable("id") String id) throws InterruptedException{
        //系统异常 会调用通用服务降级 需要加上@HystrixCommand注解
        int age = 10/0;
        Result r = paymentService.getPaymentInfo_TimeOut(id);
        return r;
    }

//    Result getPaymentInfo_TimeOutHandler(@PathVariable("id")String id){
//        return Result.succeed("这里是Hystrix80服务,当前系统繁忙或运行失败导致请求失败,请您稍后重试,我们为给您带来的不便表示抱歉");
//    }

    public Result payment_Global_FallbackMethod(){
        return Result.succeed("这是通用的服务降级处理机制");
    }
}
