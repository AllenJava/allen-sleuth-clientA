package com.allen;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Application {
	
	private final Logger logger=Logger.getLogger(getClass());
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@RequestMapping(value="/clientA/getMessage")
	public String getMessage(){
		logger.info("=== call clientA ===");
		return this.restTemplate().getForObject("http://client-B/createMessage", String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
