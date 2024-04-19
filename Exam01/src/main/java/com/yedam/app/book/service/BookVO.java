package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookVO {
	private Integer bookNo;
	private String bookName;
	private String bookCovering;
	@DateTimeFormat(pattern = "YYYY/MM/DD")
	private Date   bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
}
