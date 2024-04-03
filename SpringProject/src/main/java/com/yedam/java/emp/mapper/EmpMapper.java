package com.yedam.java.emp.mapper;

import java.util.List;

import com.yedam.java.emp.service.EmpVO;

//인터페이스 > 빈 생성하지 않음
public interface EmpMapper {
	public List<EmpVO> selectEmpAll();
}
