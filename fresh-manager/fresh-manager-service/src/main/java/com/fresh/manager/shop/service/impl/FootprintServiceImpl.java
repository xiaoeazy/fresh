package com.fresh.manager.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.manager.mapper.shop.FootprintMapper;
import com.fresh.manager.pojo.shop.FootprintEntity;
import com.fresh.manager.shop.service.FootprintService;


@Service("footprintService")
public class FootprintServiceImpl implements FootprintService {
	@Autowired
	private FootprintMapper footprintMapper;
	
	@Override
	public FootprintEntity queryObject(Integer id){
		return footprintMapper.queryObject(id);
	}
	
	@Override
	public List<FootprintEntity> queryList(Map<String, Object> map){
		return footprintMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return footprintMapper.queryTotal(map);
	}
	
	@Override
	public void save(FootprintEntity footprint){
		footprintMapper.save(footprint);
	}
	
	@Override
	public void update(FootprintEntity footprint){
		footprintMapper.update(footprint);
	}
	
	@Override
	public void delete(Integer id){
		footprintMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		footprintMapper.deleteBatch(ids);
	}
	
}
