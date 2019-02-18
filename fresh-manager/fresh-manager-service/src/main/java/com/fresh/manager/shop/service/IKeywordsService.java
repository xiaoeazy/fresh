package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Keywords;

public interface IKeywordsService {

	Keywords queryById(Integer id);

	List<Keywords> queryList(Keywords keywords, Integer pageNum, Integer pageSize);

	long queryTotal(Keywords keywords);

	void insertSelective(Keywords keywords);

	void updateByPrimaryKeySelective(Keywords keywords);

	void deleteBatch(List<Integer> values);

  
}
