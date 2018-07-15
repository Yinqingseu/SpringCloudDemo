package com.yinqing.serviceribbon;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableHystrix //程序启动类加@EnableHystrix注解开启Hystrix断路器功能
@EnableDiscoveryClient
@SpringBootApplication
//在主程序启动类中加入@EnableHystrixDashboard注解，开启hystrixDashboard断路器：Hystrix 仪表盘
@EnableHystrixDashboard
public class ServiceribbonApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceribbonApplication.class, args);
    }

    @Bean //向程序的ioc注入一个bean : restTemplate
    @LoadBalanced //表明这个restRemplate开启负载均衡的功能。
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}

