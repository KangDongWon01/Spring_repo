package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

//@Data 로 적는것보다는 필요한 값... 롬복은 생각보다 많은 부분을 보강할 수 있음

@Getter
@Setter
public class BookVO {
//데이터를 담는 대상. DB에 관련한 필드를 정의하는 곳.
	private Integer bookNo;
	private String bookName;
	private String bookCovering;
	//기본 날짜 포맷(디폴트 인정 포맷) yyyy/MM/dd
	//html에서 input 태그에 date 타입을 넣게 되면, yyyy-mm-dd 로 들어오게 됨
	//서버와 엔드포인트에서 서로 다른 포맷을 쓰는 경우가 잦으므로 아래와 같은 포맷을 설정해주어야만 함
	//포맷은 한 종류만 받을 수 있음. yyyy-MM-dd랑 yyyy/MM/dd를 같이 받을 수는 없다는 소리임
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
	
	private int rentCount; //rent_count
	private int rentTotalPrice; // rent_total_price
	
}
