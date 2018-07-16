package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:06 2018/7/11
 * @Modified By:
 */
@RestController
public class helloController {
    private final static Logger logger = LoggerFactory.getLogger(helloController.class);
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        int sleepTime = new Random().nextInt(1000);
        logger.info("sleepTime: "+sleepTime);
        Thread.sleep(sleepTime);
        return "hello-service!";
    }

    @GetMapping("/cacheTest")
    public Integer cacheTest() throws InterruptedException {
        int sleepTime = new Random().nextInt(4000);
        logger.info("sleepTime: "+sleepTime);
        Thread.sleep(sleepTime);
        return sleepTime;
    }
}
