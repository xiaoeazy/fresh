package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Goodsattribute;

public interface IGoodsAttributeService {

	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * 
	 * @param goodsattribute
	 */
	void updateByPrimaryKeySelective(Goodsattribute goodsattribute);

	/**
	 * 插入
	 * 
	 * @param goodsattribute
	 */
	void insertSelective(Goodsattribute goodsattribute);

	/**
	 * 查询总数
	 * 
	 * @param goodsattribute
	 */
	long queryTotal(Goodsattribute goodsattribute);

	/**
	 * 查询列表
	 * 
	 * @param goodsattribute
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Goodsattribute> queryList(Goodsattribute goodsattribute, Integer pageNum, Integer pageSize);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Goodsattribute queryById(Integer id);

}
