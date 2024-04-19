package com.yedam.app.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AaaMapper;
import com.yedam.app.aop.service.AaaService;

@Service
public class AaaServiceImpl implements AaaService {
	
	@Autowired
	AaaMapper aaaMapper;
	
	//이 어노테이션이 없었다면, 각 인서트가 개별의 트랜잭션을 가지고 진행된다.
	// 이 어노테이션을 사용함으로서, 두 쿼리는 하나의 트랜잭션으로 묶이게 된다.
	@Transactional
	@Override
	public void insert() {
		aaaMapper.insert("101");
		aaaMapper.insert("102");
	}
}