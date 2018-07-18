package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class Trace2Application {

	private static final Log logger = LogFactory.getLog(Trace2Application.class);

	@GetMapping("/trace-2")
	public String trace(){
		logger.info("===<call trace-2>===");
		return "Trace";
	}
	public static void main(String[] args) {
		SpringApplication.run(Trace2Application.class, args);
	}
}
