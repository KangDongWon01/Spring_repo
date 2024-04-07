package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;
import com.yedam.app.dept.service.mapper.DeptMapper;

@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	DeptMapper deptMapper;

	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		return deptMapper.selectDept(deptVO);
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDept(deptVO);
		if (result == 1) {
			return deptVO.getDepartmentId();
		} else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSucced = false;
		int result = deptMapper.insertDept(deptVO);
		
		if (result == 1) {
			isSucced = true;
		}
		
		map.put("result", isSucced);
		map.put("target", deptVO);
		return map;
	}

	@Override
	public Map<String, Object> deptDelete(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		int result = deptMapper.deleteDept(deptVO.getDepartmentId());
		
		if (result == 1) {
			map.put("departmentId", deptVO.getDepartmentId());
		}
		
		return map;
	}
	
	
}