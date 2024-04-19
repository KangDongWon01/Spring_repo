package com.yedam.app.book.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookMapper bookMapper;
	
	@Override
	public List<BookVO> bookList() {
		return bookMapper.selectBookAll();
	}

	@Override
	public BookVO bookInfo(BookVO bookVo) {
		return bookMapper.selectBook(bookVo);
	}

	@Override
	public int bookInsert(BookVO bookVO) {
		int result = bookMapper.insertBook(bookVO);
			if(result == 1) {
				return bookVO.getBookNo();
			} else {
				return -1;
		}
	}

	@Override
	public Map<String, Object> bookUpdate(BookVO bookVO) {
		return null; //수정 기능 해당없음
	}

	@Override
	public Map<String, Object> bookDelete(BookVO bookVO) {
		return null; // 삭제 기능 해당없음
	}

	@Override
	public BookVO getBookNo() {
		
		return bookMapper.getBookId();
	}


}