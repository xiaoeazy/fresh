package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Channel;

public interface IChannelService {
	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * 
	 * @param channel
	 */
	void updateByPrimaryKeySelective(Channel channel);

	/**
	 * 插入
	 * 
	 * @param channel
	 */
	void insertSelective(Channel channel);

	/**
	 * 查询总数
	 * 
	 * @param channel
	 * @return
	 */
	long queryTotal(Channel channel);

	/**
	 * 查询列表
	 * 
	 * @param channel
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Channel> queryList(Channel channel, Integer pageNum, Integer pageSize);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Channel queryById(Integer id);

}
