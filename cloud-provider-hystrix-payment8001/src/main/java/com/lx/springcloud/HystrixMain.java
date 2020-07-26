package com.lx.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HystrixMain {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMain.class, args);
    }

    @Bean
    public ServletRegistrationBean getServlet(){

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

        ServletRegistrationBean regBean = new ServletRegistrationBean(streamServlet);

        regBean.setLoadOnStartup(1);

        List mappingList = new ArrayList();

        mappingList.add("/hystrix.stream");

        regBean.setUrlMappings(mappingList);

        regBean.setName("hystrixServlet");

        return regBean;

    }
}
