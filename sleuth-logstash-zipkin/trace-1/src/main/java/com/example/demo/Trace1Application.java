package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class Trace1Application {

	private static final Log logger = LogFactory.getLog(Trace1Application.class);

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@GetMapping("/trace-1")
	public String trace(){
		logger.info("===call trace-1===");
		return restTemplate().getForEntity("http://trace-2/trace-2",String.class).getBody();
	}

	public static void main(String[] args) {
		SpringApplication.run(Trace1Application.class, args);
	}
}
