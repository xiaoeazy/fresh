package com.fresh.manager.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.manager.mapper.shop.SearchHistoryMapper;
import com.fresh.manager.pojo.shop.SearchHistoryEntity;
import com.fresh.manager.shop.service.SearchHistoryService;


@Service("searchHistoryService")
public class SearchHistoryServiceImpl implements SearchHistoryService {
	@Autowired
	private SearchHistoryMapper searchHistoryMapper;
	
	@Override
	public SearchHistoryEntity queryObject(Integer id){
		return searchHistoryMapper.queryObject(id);
	}
	
	@Override
	public List<SearchHistoryEntity> queryList(Map<String, Object> map){
		return searchHistoryMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return searchHistoryMapper.queryTotal(map);
	}
	
	@Override
	public void save(SearchHistoryEntity searchHistory){
		searchHistoryMapper.save(searchHistory);
	}
	
	@Override
	public void update(SearchHistoryEntity searchHistory){
		searchHistoryMapper.update(searchHistory);
	}
	
	@Override
	public void delete(Integer id){
		searchHistoryMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		searchHistoryMapper.deleteBatch(ids);
	}
	
}
