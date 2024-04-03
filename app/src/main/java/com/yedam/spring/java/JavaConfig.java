package com.yedam.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
	
	@Bean(name = "chf")
	public Chef chef() {
		return new Chef();
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		Restaurant res = new Restaurant();
		res.setChef(chef);
		return res;
	}
}
