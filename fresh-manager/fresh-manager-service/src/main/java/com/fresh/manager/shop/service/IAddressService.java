package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Address;

public interface IAddressService {
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);
	/**
	 * 批量更新
	 * @param address
	 */
	void updateByPrimaryKeySelective(Address address);
	/**
	 * 批量插入
	 * @param address
	 */
	void insertSelective(Address address);
	/**
	 * 数量
	 * @param address
	 * @return
	 */
	long queryTotal(Address address);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Address queryById(Integer id);
	/**
	 * 查询列表
	 * @param address
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Address> queryList(Address address, Integer pageNum, Integer pageSize);
	
	/**
	 * api 更新删除
	 * @param entity
	 */
	void saveOrUpdate(Address entity);
	/**
	 * 根据主键删除
	 * @param id
	 */
	void deleteById(Integer id);

  
}
