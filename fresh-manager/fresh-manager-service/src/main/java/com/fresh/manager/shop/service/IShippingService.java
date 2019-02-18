package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Shipping;

public interface IShippingService {
	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * 
	 * @param shipping
	 */
	void updateByPrimaryKeySelective(Shipping shipping);

	/**
	 * 插入
	 * 
	 * @param shipping
	 */
	void insertSelective(Shipping shipping);

	/**
	 * 查询总数
	 * 
	 * @param shipping
	 * @return
	 */
	long queryTotal(Shipping shipping);

	/**
	 * 查询列表
	 * 
	 * @param shipping
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Shipping> queryList(Shipping shipping, Integer pageNum, Integer pageSize);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Shipping queryById(Integer id);

}
