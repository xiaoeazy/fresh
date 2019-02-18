package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Attribute;


public interface IAttributeService {
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);
	/**
	 * 更新
	 * @param attribute
	 */
	void updateByPrimaryKeySelective(Attribute attribute);
	/**
	 * 插入
	 * @param attribute
	 */
	void insertSelective(Attribute attribute);
	/**
	 * 查询总数
	 * @param attribute
	 * @return
	 */
	long queryTotal(Attribute attribute);
	/**
	 * 查询列表
	 * @param attribute
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Attribute> queryList(Attribute attribute, Integer pageNum, Integer pageSize);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Attribute queryById(Integer id);
	
}
