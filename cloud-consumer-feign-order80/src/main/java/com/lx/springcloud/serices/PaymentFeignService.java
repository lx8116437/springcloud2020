package com.lx.springcloud.serices;

import com.lx.springcloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/getPayment/{id}")
    Result getPayment(@PathVariable("id") String id);

    @GetMapping(value = "/payment/getTimeOut")
    String getTimeOut();


}
