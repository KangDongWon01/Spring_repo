package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@RestController // @Controller + 모든 메서드에 @ResponseBody를 적용하겠다
public class EmpRestController {
	@Autowired
	EmpService empService;
	
	//전체조회
	@GetMapping("emps") //uri가 아니라 자원명을 적는다.
	public List<EmpVO> empList(){
		return empService.empList();
		//페이지를 요구하지 않기 때문에 Model사용 X, 페이지에 적어 보내는게 아니라 데이터 자체를 뿌린다
	}
	
	//단건조회
	@GetMapping("emps/{id}")
	//검색조건에서는 PathVariable을 사용하지 않음. 검색조건은 여러가지인데
	//패스배리어블에서는 조건에 해당되는 값들을 전부 경로에 넣어주게 설정해야 되기 때문이다.
	//public EmpVO empInfo(@PathVariable Integer id){ //PathVariable : 경로에
	//패스배리어블은 기본타입만 들어올 수 있다. 객체같은거 못들어감
	public EmpVO empInfo(@PathVariable(name="id") Integer employeeId){ //PathVariable : 경로에 
		EmpVO findVO = new EmpVO();
		findVO.setEmployeeId(employeeId);
		
		return empService.empInfo(findVO); 
	}
	//등록 - POST
	@PostMapping("emps")
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}
	
	//수정 - PUT
	@PutMapping("emps/{id}")
	public Map<String, Object> empUpdate(@PathVariable(name="id") Integer employeeId,
			@RequestBody EmpVO empVO){ //PathVariable : 경로에 
		
		empVO.setEmployeeId(employeeId);
		
		return empService.empUpdate(empVO); 
	}
	
	//삭제
	@DeleteMapping("emps/{id}")
	public Map<String, Object> empDelete(@PathVariable(name="id") Integer employeeId){
		EmpVO findVO = new EmpVO();
		findVO.setEmployeeId(employeeId);
		
		return empService.empDelete(findVO);
	}
}
