package com.yedam.app.book.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookRentVO;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Controller
public class BookController {

	Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	// 목록
	@GetMapping("bookList")
	public String bookList(Model model) {
		//1) 해당 기능 수행 - Service
		List<BookVO> list = bookService.bookList();
		//2) 클라이언트에 전달할 데이터 담기.
		model.addAttribute("bookList", list);
		//3) 데이터를 출력할 페이지 선택.
		return "book/bookList"; // 리턴하는 경로는 맨 앞에 /를 붙이지 않는다.
	}
	
	// 목록
		@GetMapping("rentList")
		public String rentList(Model model) {
			//1) 해당 기능 수행 - Service
			List<BookRentVO> list = null;
			//2) 클라이언트에 전달할 데이터 담기.
			model.addAttribute("bookList", list);
			//3) 데이터를 출력할 페이지 선택.
			return "book/bookList"; // 리턴하는 경로는 맨 앞에 /를 붙이지 않는다.
		}
	
	//등록 - 페이지 => Get (데이터가 넘어가지 않으므로)
		@GetMapping("bookInsert")
		public String bookInsertForm(Model model) {
			//BookVO bookVO = bookService.getBookNo();
			model.addAttribute("bookVO", new BookVO());
			return "book/bookInsert";
		}

		//등록 - 처리 => Post (데이터를 리퀘스트 바디에 실어 넘겨야 하므로)
		@PostMapping("bookInsert")
		public String bookInsertProcess(BookVO bookVO) {
			int bNo = bookService.bookInsert(bookVO);
			return "book/bookList";
		}
}
