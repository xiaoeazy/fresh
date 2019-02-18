package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Specification;

public interface ISpecificationService {
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);
	/**
	 * 更新
	 * @param specification
	 */
	void updateByPrimaryKeySelective(Specification specification);
	/**
	 * 插入
	 * @param specification
	 */
	void insertSelective(Specification specification);
	/**
	 * 查询数量
	 * @param specification
	 * @return
	 */
	long queryTotal(Specification specification);
	/**
	 * 查询数量
	 * @param specification
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Specification> queryList(Specification specification, Integer pageNum, Integer pageSize);
	/**
	 * 根据主键查询
	 * @param SpecificationId
	 * @return
	 */
	Specification queryById(Integer SpecificationId);
	
	
}
