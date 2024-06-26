package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@SpringBootApplication
@MapperScan(basePackages = "com.yedam.app.**.mapper")
public class Exam01Application {

	public static void main(String[] args) {
		SpringApplication.run(Exam01Application.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "home";
	}
}
