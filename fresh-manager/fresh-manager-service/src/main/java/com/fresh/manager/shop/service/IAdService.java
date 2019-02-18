package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Ad;

public interface IAdService {
	/**
	 * 根据主键查询
	 * 
	 * @param userId
	 * @return
	 */
	Ad queryById(Integer userId);

	/**
	 * 查询列表
	 * 
	 * @param ad
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Ad> queryList(Ad ad, Integer pageNum, Integer pageSize);

	/**
	 * 查询总数
	 * 
	 * @param ad
	 * @return
	 */
	long queryTotal(Ad ad);

	/**
	 * 插入
	 * 
	 * @param ad
	 */
	void insertSelective(Ad ad);

	/**
	 * 更新
	 * 
	 * @param ad
	 */
	void updateByPrimaryKeySelective(Ad ad);

	/**
	 * 删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

}
