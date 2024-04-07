package com.yedam.app.dept.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.dept.service.DeptVO;

public interface DeptMapper {
	//전체 조회
	public List<DeptVO> selectDeptAll();
	
	//단건 조회
	public DeptVO selectDept(DeptVO deptVO);
	
	//등록
	public int insertDept(DeptVO deptVO);
	
	//수정
	public int updateDept(DeptVO deptVO);
	
	//삭제
	public int deleteDept(@Param("departmentId") int departmentId);
}