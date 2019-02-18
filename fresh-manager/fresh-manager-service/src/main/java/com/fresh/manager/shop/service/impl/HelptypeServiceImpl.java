package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.HelptypeMapper;
import com.fresh.manager.pojo.shop.Helptype;
import com.fresh.manager.pojo.shop.HelptypeExample;
import com.fresh.manager.shop.service.IHelptypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class HelptypeServiceImpl implements IHelptypeService {
	@Autowired
	private HelptypeMapper helptypeMapper;
	
	@Override
	public List<Helptype>   queryAllType(){
		HelptypeExample ge = new HelptypeExample();
		return  helptypeMapper.selectByExample(ge);
	}
}
