package com.yedam.java.emp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yedam.java.emp.mapper.EmpMapper;
import com.yedam.java.emp.service.EmpService;
import com.yedam.java.emp.service.EmpVO;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired //필드 주입 방식
	EmpMapper empMapper;

//  생성자 직접 지정 후 @Autowired > 가장 안정적인 방법
//	public EmpServiceImpl(EmpMapper empMapper) {
//		this.empMapper = empMapper;
//	}
	
	@Override
	public List<EmpVO> empAllList() {
		return empMapper.selectEmpAll();
	}

	
}
