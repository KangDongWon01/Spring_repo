package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //AOP를 위한 빈 이라는 뜻
@Component
public class LogAdvice {
	//포인트컷 : 비즈니스 로직과 관련된 메소드 중에서 Advice(공통 코드)가 적용될 대상 메소드
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")
	public void allPointCut() {}
		
	// Weaving : 포인트컷 + Advice + 타이밍 
	// 포인트컷으로 지정한 핵심관심 메소드가 호출될 때, 어드바이스에 해당하는 횡단 관심 메소드가 삽입되는 과정
	@Around("allPointCut()")
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		// Aop가 적용되는 메서드의 이름
		String signatureStr = joinPoint.getSignature().toString();
		System.out.println("어라운드 어드바이스 : " + signatureStr);
			
		//공통기능
		System.out.println("핵심 기능 전 실행 - 공통기능 : " + System.currentTimeMillis());
		try {
			Object obj = joinPoint.proceed(); //프로시드 메서드는 예외 처리가 필요함, 필요하다면 여기서 try-catch 적용 가능
			return obj;
		} finally {
			System.out.println("핵심 기능 후 실행 - 공통기능 : " + System.currentTimeMillis());
			System.out.println("끝 : " + signatureStr);
		}
	}
	
	@Before("allPointCut()")
	public void beforeAdvice(JoinPoint joinPoint) {
		String signatureStr = joinPoint.getSignature().toString();
		System.out.println("비포어 어드바이스 : " + signatureStr);	
	}
	
	
}