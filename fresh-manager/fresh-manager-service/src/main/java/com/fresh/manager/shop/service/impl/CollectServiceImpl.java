package com.fresh.manager.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.manager.mapper.shop.CollectMapper;
import com.fresh.manager.pojo.shop.CollectEntity;
import com.fresh.manager.shop.service.CollectService;


@Service("collectService")
public class CollectServiceImpl implements CollectService {
	@Autowired
	private CollectMapper collectMapper;
	
	@Override
	public CollectEntity queryObject(Integer id){
		return collectMapper.queryObject(id);
	}
	
	@Override
	public List<CollectEntity> queryList(Map<String, Object> map){
		return collectMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return collectMapper.queryTotal(map);
	}
	
	@Override
	public void save(CollectEntity collect){
		collectMapper.save(collect);
	}
	
	@Override
	public void update(CollectEntity collect){
		collectMapper.update(collect);
	}
	
	@Override
	public void delete(Integer id){
		collectMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		collectMapper.deleteBatch(ids);
	}
	
}
