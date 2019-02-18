package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Userlevel;

public interface IUserLevelService {
	/**
	 * 查询
	 * @param userlevel
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Userlevel> queryList(Userlevel userlevel, Integer pageNum, Integer pageSize);
	/**
	 * 查询数量
	 * @param userlevel
	 * @return
	 */
	long queryTotal(Userlevel userlevel);
	/**
	 * 插入信息
	 * @param userlevel
	 */
	void insertSelective(Userlevel userlevel);
	/**
	 * 更新数据
	 * @param userlevel
	 */
	void updateByPrimaryKeySelective(Userlevel userlevel);
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Short> values);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	Userlevel queryById(Short id);

    
}
