package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Ordergoods;

public interface IOrderGoodsService {
	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * 
	 * @param ordergoods
	 */
	void updateByPrimaryKeySelective(Ordergoods ordergoods);

	/**
	 * 插入
	 * 
	 * @param ordergoods
	 */
	void insertSelective(Ordergoods ordergoods);

	/**
	 * 查询总数
	 * 
	 * @param ordergoods
	 * @return
	 */
	long queryTotal(Ordergoods ordergoods);

	/**
	 * 查询列表
	 * 
	 * @param ordergoods
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Ordergoods> queryList(Ordergoods ordergoods, Integer pageNum, Integer pageSize);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Ordergoods queryById(Integer id);
	/**
	 * 根据orderid 查询
	 * @param orderId
	 * @return
	 */
	List<Ordergoods> queryByOrderId(Integer orderId);

}
