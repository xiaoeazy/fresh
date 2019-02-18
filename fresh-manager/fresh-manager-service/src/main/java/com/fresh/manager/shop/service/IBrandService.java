package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Brand;


public interface IBrandService {

	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);
	/**
	 * 更新
	 * @param brand
	 */
	void updateByPrimaryKeySelective(Brand brand);
	/**
	 * 插入
	 * @param brand
	 */
	void insertSelective(Brand brand);
	/**
	 * 查询总数
	 * @param brand
	 * @return
	 */
	long queryTotal(Brand brand);
	/**
	 * 查询所有
	 * @param brand
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Brand> queryList(Brand brand, Integer pageNum, Integer pageSize);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Brand queryById(Integer id);
}
