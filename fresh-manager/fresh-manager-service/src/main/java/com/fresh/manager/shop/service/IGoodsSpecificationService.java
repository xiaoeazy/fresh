package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Goodsspecification;

/**
 * 商品对应规格表值表Service接口
 *
 */
public interface IGoodsSpecificationService {
	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * 
	 * @param Goodsspecification
	 */
	void updateByPrimaryKeySelective(Goodsspecification goodsspecification);

	/**
	 * 插入
	 * 
	 * @param Goodsspecification
	 */
	void insertSelective(Goodsspecification goodsspecification);

	/**
	 * 查询所有
	 * 
	 * @param Goodsspecification
	 * @return
	 */
	int queryTotal(Goodsspecification goodsspecification);

	/**
	 * 查询列表
	 * 
	 * @param Goodsspecification
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Goodsspecification> queryList(Goodsspecification goodsspecification, Integer pageNum, Integer pageSize);

	/**
	 * 根据主键查询
	 * 
	 * @param GoodsspecificationId
	 * @return
	 */
	Goodsspecification queryById(Integer goodsspecificationId);

}
