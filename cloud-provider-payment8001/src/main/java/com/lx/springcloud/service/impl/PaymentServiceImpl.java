package com.lx.springcloud.service.impl;

import com.lx.springcloud.dao.PaymentDao;
import com.lx.springcloud.entities.Payment;
import com.lx.springcloud.entities.Result;
import com.lx.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao paymentDao;
    @Override
    public Result getPaymentById(String id) {
        Payment p = paymentDao.getPaymentById(id);
        return Result.succeed(p);
    }

    @Override
    public Result insertPayment(Payment payment) {
        payment.setId(UUID.randomUUID().toString().replaceAll("-",""));
        int i = paymentDao.insertPayment(payment);
        return Result.succeed(i);
    }
}
