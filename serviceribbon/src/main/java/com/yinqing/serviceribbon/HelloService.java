package com.yinqing.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    //通过之前注入ioc容器的restTemplate来消费eureka-client服务的“/hi”接口
    @Autowired
    RestTemplate restTemplate;

    //HystrixCommand注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){
        //这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
        // 根据服务实例在请求的时候会用具体的url替换掉服务名
        //在浏览器上多次访问http://localhost:8764/hi?name=forezp，浏览器交替显示：
        // hi forezp,i am from port:8762
        //hi forezp,i am from port:8763
        //说明当我们通过调用restTemplate.getForObject(“http://SERVICE-HI/hi?name=“+name,String.class)方法时，
        // 已经做了负载均衡，访问了不同的端口的服务实例。
        return restTemplate.getForObject("http://EUREKA-CLIENT/hi?name="+name,String.class); //发送get请求
    }

    public String hiError(String name){
        return "hi, "+name+",sorry,error!";
    }

}
