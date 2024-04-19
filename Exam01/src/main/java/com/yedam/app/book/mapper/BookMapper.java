package com.yedam.app.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.book.service.BookVO;

public interface BookMapper {
	// 전체조회
	public List<BookVO> selectBookAll();
	
	// 단건조회
	public BookVO selectBook(BookVO bookVo);
	
	// 등록
	public int insertBook(BookVO bookVo);
	
	// 수정
	public int updateBook(BookVO bookVo);
	
	// 삭제
	public int deleteBook(@Param("bookNo")int bookNo);
	
	// 등록창에서 bookNo 자동으로 가져오기 (셀렉트키)
	public BookVO getBookId();

}