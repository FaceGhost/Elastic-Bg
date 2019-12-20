package com.faceghost.elasticbg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ElasticBgEurekaApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ElasticBgEurekaApplication.class, args);
	}

}
