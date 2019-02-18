package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Product;

/**
 * Service接口
 */
public interface IProductService {
	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Product queryById(Integer id);

	/**
	 * 查询总数
	 * 
	 * @param product
	 * @return
	 */
	long queryTotal(Product product);

	/**
	 * 更新
	 * 
	 * @param product
	 */
	void updateByPrimaryKeySelective(Product product);

	/**
	 * 删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 插入
	 * 
	 * @param product
	 */
	void insertSelective(Product product);

	/**
	 * 查询所有
	 * 
	 * @param product
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Product> queryList(Product product, Integer pageNum, Integer pageSize);

}
