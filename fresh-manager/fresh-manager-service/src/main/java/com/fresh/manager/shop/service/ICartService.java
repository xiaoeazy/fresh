package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Cart;


public interface ICartService {
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);
	/**
	 * 更新
	 * @param cart
	 */
	void updateByPrimaryKeySelective(Cart cart);
	/**
	 * 插入
	 * @param cart
	 */
	void insertSelective(Cart cart);
	/**
	 * 查询总数
	 * @param cart
	 * @return
	 */
	long queryTotal(Cart cart);
	/**
	 * 查询
	 * @param cart
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Cart> queryList(Cart cart, Integer pageNum, Integer pageSize);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Cart queryById(Integer id);
	
	
}
