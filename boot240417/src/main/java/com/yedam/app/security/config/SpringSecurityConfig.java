package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //트랜잭션 매니저처럼, 자신의 성질을 어노테이션으로 등록(?)
public class SpringSecurityConfig {
	
	// 암호화를 처리하는 빈
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //입력한 패스워드와 DB에 저장된 패스워드 같은지 체크하는 함수 (해쉬를 이용함.. 근데 해쉬가 뭔지 잘 모르겠음)	
		
	}
	
	// 메모리상 인증정보 활용
	//@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
							   .username("user1")
							   .password(passwordEncoder().encode("1234"))
							   .roles("USER", "SALES")
							   //.authorities("ROLE_USER") //권한은 복수로 부여 가능
							   .build();
		return new InMemoryUserDetailsManager(user);
	}
	
	// 인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests() // 각 URL별로 어떤 인증, 어떤 인가를 체크해야 하는가... 위에서 아래로 체크해 내려감(순서가 뒤바뀌면 아래쪽은 동작 불가)
				.antMatchers("/", "/all").permitAll() //인증받지 않은 대상도 포함하여 인가하는 메서드 permitAll
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN") //ROLE_USER... hasRole...
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
				.and().formLogin()
					.defaultSuccessUrl("/all")
				.and()
					.logout().logoutSuccessUrl("/all");
		
		http.csrf();//.disable();
		
		return http.build();
	}
	
	
	
	
	
}
