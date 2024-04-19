package com.yedam.app.book.service;

import java.util.List;
import java.util.Map;

public interface BookService {
	// 전체조회
			public List<BookVO> bookList();
			
			// 단건조회
			public BookVO bookInfo(BookVO bookVo);
			
			// 등록
			public int bookInsert(BookVO bookVO);
		
			// 수정
			public Map<String, Object> bookUpdate(BookVO bookVO);
			
			// 삭제
			public Map<String, Object> bookDelete(BookVO bookVO);
			
			// 등록할 사원번호 조회
			public BookVO getBookNo();
}

