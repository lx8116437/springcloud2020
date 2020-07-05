package com.lx.springcloud.dao;

import com.lx.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    Payment getPaymentById(@Param("id") String id);

    int insertPayment(Payment payment);
}
