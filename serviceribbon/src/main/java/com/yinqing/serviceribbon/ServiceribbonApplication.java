package com.yinqing.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableHystrix //程序启动类加@EnableHystrix注解开启Hystrix
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceribbonApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceribbonApplication.class, args);
    }

    @Bean //向程序的ioc注入一个bean : restTemplate
    @LoadBalanced //表明这个restRemplate开启负载均衡的功能。
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

