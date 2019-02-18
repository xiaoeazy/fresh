package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Category;

public interface ICategoryService {
	/**
	 * 根据主键查询
	 * @param userId
	 * @return
	 */
	Category queryById(Integer userId);
	/**
	 * 查询列表
	 * @param category
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Category> queryList(Category category, Integer pageNum, Integer pageSize);
	/**
	 * 查询数量
	 * @param category
	 * @return
	 */
	long queryTotal(Category category);
	/**
	 * 插入
	 * @param category
	 */
	void insertSelective(Category category);
	/**
	 * 更新
	 * @param category
	 */
	void updateByPrimaryKeySelective(Category category);
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

   
}
