package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Goodsissue;

public interface IGoodsIssueService {
	/**
	 * 根据主键查询
	 * @param userId
	 * @return
	 */
	Goodsissue queryById(Integer id);
	/**
	 * 查询所有
	 * @param goodsissue
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Goodsissue> queryList(Goodsissue goodsissue, Integer pageNum, Integer pageSize);
	/**
	 * 查询总数
	 * @param goodsissue
	 * @return
	 */
	long queryTotal(Goodsissue goodsissue);
	/**
	 * 插入
	 * @param goodsissue
	 */
	void insertSelective(Goodsissue goodsissue);
	/**
	 * 更新
	 * @param goodsissue
	 */
	void updateByPrimaryKeySelective(Goodsissue goodsissue);
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

  
}
