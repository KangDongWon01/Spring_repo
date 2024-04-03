package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yedam.app.**.mapper") //경로 설정이기 때문인지, properties가 아닌 메인에서 정의함
public class Boot240403Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot240403Application.class, args);
	}

}
