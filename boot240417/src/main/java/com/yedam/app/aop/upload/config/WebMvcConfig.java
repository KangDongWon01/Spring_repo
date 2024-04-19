package com.yedam.app.aop.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{ //WebMvcConfigurer Default void로 되어있기 때문에 오버라이드 할 필요가 없다..
	// 빈으로 등록될 것인데, Default라서 그냥 따로 오버라이드 안하면 디폴트대로 작동하고
	// 오버라이드를 따로 하면 커스텀이 된 채로 작동한다.
	
	@Value("${file.upload.path}") //내부 코드가 아니라, 외부(프로퍼티즈 등등을 읽어들일 때 사용....) 자신에게 있는 필드, 변수에 담는다.
	private String uploadPath;
	//리소스 핸들링
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:///" + uploadPath, ""); //매핑
				//.addResourceLocations ....처럼 체인식으로 매핑 가능함.
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	// 인터셉터 등록
	
	// 추가적인 뷰 리졸버 등록
	
	// 리소스에 대한 핸들링 < -- 이 파일에서 해볼 주제
	
}
