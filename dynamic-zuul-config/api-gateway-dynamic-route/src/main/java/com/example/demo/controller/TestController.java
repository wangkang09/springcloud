package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:28 2018/7/16
 * @Modified By:
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${from}")
    private String from;

    @GetMapping("/from")
    public String from(){
        return this.from;
    }
}
