package com.waylau.spring.cloud.weather.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Hello Controller.
 * 
 * @since 1.0.0 2017年11月20日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@RestController
public class HelloController {
	//@RequestMapping("/hello")
	@GetMapping("/helloServer")
	public String hello() {
		return "Hello World!";
	}
}
