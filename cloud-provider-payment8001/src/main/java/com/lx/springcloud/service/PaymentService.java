package com.lx.springcloud.service;

import com.lx.springcloud.entities.Payment;
import com.lx.springcloud.entities.Result;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    Result getPaymentById(String id);

    Result insertPayment(Payment payment);
}
