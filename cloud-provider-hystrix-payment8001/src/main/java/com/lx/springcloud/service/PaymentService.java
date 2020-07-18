package com.lx.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.lx.springcloud.entities.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentService {

    public Result getPaymentInfo_OK(String id){
        String s = "请求成功,线程池:" + Thread.currentThread().getName() +"  PaymentInfo_OK  ID:" + id + "O(∩_∩)O哈哈哈~";
        return Result.succeed(s);
    }

//    @HystrixCommand(fallbackMethod = "getPaymentInfo_TimeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
//    public Result getPaymentInfo_TimeOut(String id){
//        //超时会服务降级
////        try {
////            TimeUnit.SECONDS.sleep(5);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//        //运行异常也会服务降级
////        int age = 10/0;
//
//        //正常则不会服务降级
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String s = "请求成功,线程池:" + Thread.currentThread().getName() +"  PaymentInfo_TimeOut  ID:" + id + "O(∩_∩)O哈哈哈~ 耗时(秒)" + 2;
//        return Result.succeed(s);
//    }
//
//    public Result getPaymentInfo_TimeOutHandler(String id){
//        String s = "请求成功,线程池:" + Thread.currentThread().getName() +"8001系统繁忙或运行异常,请稍后重试/(ㄒoㄒ)/~~";
//        return Result.succeed(s);
//    }

//    @HystrixCommand(fallbackMethod = "getPaymentInfo_TimeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    public Result getPaymentInfo_TimeOut(String id) throws InterruptedException {
        String s = "请求成功,线程池:" + Thread.currentThread().getName() +"  PaymentInfo_TimeOut  ID:" + id + "/(ㄒoㄒ)/~~" + 2;
        return Result.succeed(s);
    }

    public Result getPaymentInfo_TimeOutHandler(String id){
        String s = "请求成功,线程池:" + Thread.currentThread().getName() +"8001系统繁忙或运行异常,请稍后重试/(ㄒoㄒ)/~~";
        return Result.succeed(s);
    }


    //----------------------------------------熔断器--------------------------------------------
    @HystrixCommand(fallbackMethod = "getPaymentCircuitBreakerHandler",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"), //是否开启熔断器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "50000"), // 窗口期时间
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60") // 错误失败率
    })
    public Result getPaymentCircuitBreaker(int id) throws TimeoutException {
        if(id < 0){
            throw new TimeoutException("id不能为负数");
        }
        String serializable = IdUtil.simpleUUID();
        return Result.succeed("查询成功,流水号是:" + serializable);
    }

    public Result getPaymentCircuitBreakerHandler(int id){
        return Result.succeed("查询失败,id不能为负数,服务降级,您输入的id为:" + id);
    }

}
