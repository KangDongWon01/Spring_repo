package com.yedam.app.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	// GET : 조회, (값을 입력 받는)빈 페이지 / 혹은 삭제(PK하나만 받아 없애버리면 그만이기 때문에 GET을 가끔 쓴다.)  
	
	// POST: 데이터 조작(등록, 수정, 삭제)
	
	// 전체조회
	@GetMapping("empList") //Model = Request + Response
	public String empList(Model model) { //매개변수로 Session 도 들고 올 수 있다.
		//1) 해당 기능 수행 - Service
		List<EmpVO> list = empService.empList();
		//2) 클라이언트에 전달할 데이터 담기.
		model.addAttribute("empList", list);
		//3) 데이터를 출력할 페이지 선택.
		return "emp/list"; // 리턴하는 경로는 맨 앞에 /를 붙이지 않는다.
		//prefix => classpath:/templates/
		//subfix => .html
	}
	
	//단건조회 커맨드객체, 리퀘스트파람 등등... 방법은 다양함
	// 어노테이션 안 붙이는 커맨드 객체방법 선택
	//=> 보내는 방식 QueryString
	@GetMapping("empInfo") 
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "emp/info";
	}
	
	//등록 - 페이지 => Get (데이터가 넘어가지 않으므로)
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		model.addAttribute("empVO", new EmpVO());
		return "emp/insert";
	}
	
	//등록 - 처리 => Post (데이터를 리퀘스트 바디에 실어 넘겨야 하므로)
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eId = empService.empInsert(empVO);
		String uri = null;
		if(eId > -1) {
			uri = "redirect:empInfo?employeeId=" + eId;
		} else {
			uri = "empList";
		}
		return uri;
	}
	
}
