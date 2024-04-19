package com.yedam.app.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String homePage() {
		return "home";
		// 원래 적어야 할 경로 : >> classpath:/templates/home.html
		// -> application.properties 에서 prefix와 subfix로 설정해놨기 때문에 home만 달랑 적어도 되는것
	}
}
