package com.springboot.enterpriseCorporativeAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioAccountEnterpriseCorporativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioAccountEnterpriseCorporativeApplication.class, args);
	}

}
