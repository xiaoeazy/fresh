package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Goodsgallery;

public interface IGoodsGalleryService {
	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * 
	 * @param goodsgallery
	 */
	void updateByPrimaryKeySelective(Goodsgallery goodsgallery);

	/**
	 * 插入
	 * 
	 * @param goodsgallery
	 */
	void insertSelective(Goodsgallery goodsgallery);

	/**
	 * 根据主键查询
	 * 
	 * @param GoodsgalleryId
	 * @return
	 */
	Goodsgallery queryById(Integer GoodsgalleryId);

	/**
	 * 查询总数
	 * 
	 * @param goodsgallery
	 * @return
	 */
	long queryTotal(Goodsgallery goodsgallery);

	/**
	 * 查询列表
	 * 
	 * @param goodsgallery
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Goodsgallery> queryList(Goodsgallery goodsgallery, Integer pageNum, Integer pageSize);

}
