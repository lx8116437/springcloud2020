package com.lx.springcloud.service;

import com.lx.springcloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentServiceFallBack.class)
@Service
public interface PaymentService {
    @RequestMapping(value = "/hystrix/payment/getPaymentInfo_OK/{id}")
    Result getPaymentInfo_OK(@PathVariable("id") String id);

    @RequestMapping(value = "/hystrix/payment/getPaymentInfo_TimeOut/{id}")
    Result getPaymentInfo_TimeOut(@PathVariable("id") String id) throws InterruptedException;
}
