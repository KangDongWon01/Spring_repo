package com.yedam.app.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@Controller
public class HomeController {
	//커맨드 객체 방식 겟이든, 포스트든 쿼리스트링만 유지
	//QuertyString => 커맨드 객체.. 객체로 요청 내용을 주고받는다.
	//Method는 상관없음
	//Content-type : application/x-www-form-urlencoded (쿼리스트링 포맷)
	@RequestMapping(path="comobj", method= {RequestMethod.GET, RequestMethod.POST})
//	@ResponseBody
	public String commandObject(@ModelAttribute("emp")EmpVO empVO) {
		log.info("path : /comobj");
		log.info("employee_id : " + empVO.getEmployeeId());
		log.info("last_name : " + empVO.getLastName());
		log.info(empVO.toString());
		return "home";
		//classpath:/templates/ home .html
	}
	
	@RequestMapping(path="reqparm", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, String lastName, 
								@RequestParam(name = "message", defaultValue = "No message", required=false) String msg) {
	log.info("path : /comobj");
	log.info("employee_id : " + employeeId);
	log.info("last_name : " + lastName);
	log.info("msg : " + msg);
	log.info(toString());
	return "";
	}
	
	// 경로에 포함 -> 기본타입 매개변수
	// Method는 상관없음
	// Content-type는 상관없음
	@RequestMapping(path="path/{id}")
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		log.info("path : /path/{id}"); //여기서는 디폴트밸류 사용 불가함. > 통신 성공이 전제된 속성임
		log.info("= id: " + id);
		return "";
	}
	
	@PostMapping("resbody")
	@ResponseBody 
	//Json이라는 객체를 처리하기 위해서 바디부터 요청. 메소드 중 포스트와 풋만이 바디를 가지고 있음.
	//리스폰스 바디. 값을 받을 때, 메소드든, 컨텐츠타입이든 상관 안함
	public Map<String, Object> requestBody(@RequestBody EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		map.put("path", "resbody");
		map.put("data", empVO);
		return map;
	}
	
	
}

