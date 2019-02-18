package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Group;

public interface IGroupService {
	/**
	 * 查询所有关联产品信息
	 * 
	 * @param group
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Group> queryListWithGoods(Group group, Integer pageNum, Integer pageSize);

	/**
	 * 根据主键查询并关联产品信息
	 * 
	 * @param group
	 * @return
	 */
	Group queryListWithGoodsByGroupId(Group group);

	/**
	 * 查询总数
	 * 
	 * @param group
	 * @return
	 */
	long queryTotal(Group group);

	/**
	 * 删除
	 * 
	 * @param values
	 * @return
	 */
	int deleteBatch(List<Integer> values);

	/**
	 * 关于主键删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 更新
	 * 
	 * @param group
	 * @return
	 */
	int update(Group group);

	/**
	 * 保存
	 * 
	 * @param group
	 * @return
	 */
	int save(Group group);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Group queryById(Integer id);
	/**
	 * 查询列表
	 * @param group
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Group> queryList(Group group, Integer pageNum, Integer pageSize);

}
