package com.example.demo.controller;

import com.example.demo.service.HelloService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:56 2018/7/11
 * @Modified By:
 */
@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer(){
        return helloService.helloService();
    }

    @GetMapping("/cacheTest")
    public Integer cacheTest(){
        HystrixRequestContext.initializeContext();
        int s1 = helloService.cacheTest(1L);
        int s2 = helloService.cacheTest(1L);
        helloService.flushCacheByAnnotation1(1L);
        int s3 = helloService.cacheTest(1L);
        int s4 = helloService.cacheTest(2L);
        return helloService.cacheTest(1L); //和s3一样，断路与否只跟s3有关
    }
}
