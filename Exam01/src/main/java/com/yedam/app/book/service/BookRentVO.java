package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookRentVO {
	private Integer rentNo;
	private Integer bookNo;
	private int rentPrice;
	@DateTimeFormat(pattern = "YYYY/MM/DD")
	private Date rentDate;
	private  String status;
	
}
