package com.management.grocery;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("!test")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableConfigurationProperties
@EnableFeignClients
@EnableSpringDataWebSupport
public class GroceryManagementSpringBootApplication {

	@Resource
	private ApplicationContext springContext;
	
	public static void main(String[] args) {
		SpringApplication.run(GroceryManagementSpringBootApplication.class, args);
	}

	
}
