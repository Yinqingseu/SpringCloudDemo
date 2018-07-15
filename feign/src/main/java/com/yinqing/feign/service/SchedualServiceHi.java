package com.yinqing.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


//通过@ FeignClient（“服务名”），来指定调用哪个服务。
@Component //解决接口引用在controller不能自动装配问题
@FeignClient(value="eureka-client",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
    @RequestMapping(value="/hi",method=RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value="name") String name);
}

