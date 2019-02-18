package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Attributecategory;


public interface IAttributeCategoryService {
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);
	/**
	 * 更新
	 * @param attributecategory
	 */
	void updateByPrimaryKeySelective(Attributecategory attributecategory);
	/**
	 * 插入
	 * @param attributecategory
	 */
	void insertSelective(Attributecategory attributecategory);
	/**
	 * 查询数量
	 * @param attributecategory
	 * @return
	 */
	long queryTotal(Attributecategory attributecategory);
	/**
	 * 分页查询
	 * @param attributecategory
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Attributecategory> queryList(Attributecategory attributecategory, Integer pageNum, Integer pageSize);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Attributecategory queryById(Integer id);

   
}
