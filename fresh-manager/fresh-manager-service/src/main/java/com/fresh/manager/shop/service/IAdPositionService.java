package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Adposition;

public interface IAdPositionService {
	/**
	 * 根据主键查询
	 * 
	 * @param AdpositionId
	 * @return
	 */
	Adposition queryById(Integer AdpositionId);

	/**
	 * 查询
	 * 
	 * @param adposition
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Adposition> queryList(Adposition adposition, Integer pageNum, Integer pageSize);

	/**
	 * 查询总数
	 * 
	 * @param adposition
	 * @return
	 */
	long queryTotal(Adposition adposition);

	/**
	 * 插入
	 * 
	 * @param adposition
	 */
	void insertSelective(Adposition adposition);

	/**
	 * 更新
	 * 
	 * @param adposition
	 */
	void updateByPrimaryKeySelective(Adposition adposition);

	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

}
