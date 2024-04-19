package com.yedam.app.upload.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfig {
		
	
		//멀티파트리졸버는 인터페이스이고, 인터페이스는 객체 생성이 안되므로 빈으로 등록이 안됨
		//그래서, 아래와 같은 형태를 취함. 구현클래스를 리턴 한다는 것 같은데, 의미 해석을 해 볼것
		@Bean
		public MultipartResolver multipartResolver() {
			return new StandardServletMultipartResolver();
		}
		
		@Bean
		public MultipartConfigElement multipartConfigElement() {
			MultipartConfigFactory factory = new MultipartConfigFactory();
			factory.setMaxRequestSize(DataSize.ofMegabytes(10));
			factory.setMaxFileSize(DataSize.ofBytes(0));
			
			return factory.createMultipartConfig();
		}
		
}
