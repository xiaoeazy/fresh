package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Groupgoods;

public interface IGroupGoodsService {

	/**
	 * 插入
	 * @param groupGoods
	 * @return
	 */
	int insertSelective(Groupgoods groupGoods);

	/**
	 * 根据主键更新
	 * @param groupGoods
	 * @return
	 */
	int updateByPrimaryKey(Groupgoods groupGoods);

	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);
	
	/**
	 * 批量删除
	 * @param values
	 * @return
	 */
	int deleteBatch(List<Integer> values);
	
	/**根据goodsid 批量删除
	int deleteBatchByGoodsId(List<Integer> values);
	/**
	 * 根据groupid批量删除
	 * @param values
	 * @return
	 */
	int deleteBatchByGroupId(List<Integer> values);
	/**
	 * 根据goodsid进行删除
	 * @param values
	 * @return
	 */
	int deleteBatchByGoodsId(List<Integer> values);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Groupgoods queryById(Integer id);

	/**
	 * 查询所有
	 * @param groupgoods
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Groupgoods> queryList(Groupgoods groupgoods, Integer pageNum, Integer pageSize);

	List<Groupgoods> queryListWithGoodsInfo(Groupgoods obj);

	long queryTotal(Groupgoods groupgoods);

	

}
