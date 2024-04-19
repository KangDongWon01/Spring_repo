package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yedam.app.aop.service.AaaService;

@SpringBootTest
class Boot240417ApplicationTests {

	@Autowired
	AaaService aaaService;
	
	@Test
	void contextLoads() {
		aaaService.insert();
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	// 단방향 암호화 : 복구가 불가능한 암호 형태 
	
	@Test
	public void testEncoder() {
		String password = "1234";
		
		String enPwd = passwordEncoder.encode(password);
		System.out.println("enPwd : " + enPwd);
	
	boolean matchResult = passwordEncoder.matches(password, enPwd);
	System.out.println("matchResult : " + matchResult);
	}

}
