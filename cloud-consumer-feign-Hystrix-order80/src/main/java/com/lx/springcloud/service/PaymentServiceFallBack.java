package com.lx.springcloud.service;

import com.lx.springcloud.entities.Result;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallBack implements PaymentService{
    @Override
    public Result getPaymentInfo_OK(String id) {
        return Result.succeed("PaymentService-----getPaymentInfo_OK--/(ㄒoㄒ)/~~");
    }

    @Override
    public Result getPaymentInfo_TimeOut(String id) throws InterruptedException {
        return Result.succeed("PaymentService-----getPaymentInfo_TimeOut--/(ㄒoㄒ)/~~");
    }
}
