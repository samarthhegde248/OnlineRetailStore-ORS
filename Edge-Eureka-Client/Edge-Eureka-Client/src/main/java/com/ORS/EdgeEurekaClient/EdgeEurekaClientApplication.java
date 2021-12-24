package com.ORS.EdgeEurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@RefreshScope
@EnableEurekaClient
@SpringBootApplication
public class EdgeEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeEurekaClientApplication.class, args);
	}

}
