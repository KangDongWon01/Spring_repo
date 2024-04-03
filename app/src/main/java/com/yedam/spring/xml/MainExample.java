package com.yedam.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {

	public static void main(String[] args) {
		ApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Restaurant res = (Restaurant)ctx.getBean(Restaurant.class);
		Restaurant second  = (Restaurant)ctx.getBean(Restaurant.class);
		
		if( res == second) {
			System.out.println("동등한 인스턴스입니다.");
		}else {
			System.out.println("다른 인스턴스입니다.");
		}
		
		res.run();
		second.run();
	}

}
