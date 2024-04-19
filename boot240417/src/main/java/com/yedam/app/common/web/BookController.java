package com.yedam.app.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	//도서전체조회 - Get
	@GetMapping("bookList")
	public String bookList(Model model) {
		List<BookVO> list = bookService.bookList();
		model.addAttribute("books",list);
		return "book/list";
		// classpath:/templates/book/list.html
		// 역시 prefix와 subfix로 인해 경로가 단축됨
		// 단, 스프링 부트의 경우 기본 설정에 prefix, subfix가 생겨서
		// 프로퍼티즈 파일에 등록할 필요가 없는 것.
	}
	
	@GetMapping("bookInsert")
	public String bookInsertForm(Model model) {
		BookVO bookVO = bookService.getBookNo();
		model.addAttribute("book", bookVO);
		return "book/insert";
		// classpath:/templates/book/insert.html
	}
	
	
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {
		bookService.bookInfoInsert(bookVO);
		return "redirect:bookList";
	}
	
	@GetMapping("rentInfo")
	public String rentInfo(Model model) {
		List<BookVO> list = bookService.rentInfo();
		return "book/rent";
		// 페이지는 만들어보기
	}
}
