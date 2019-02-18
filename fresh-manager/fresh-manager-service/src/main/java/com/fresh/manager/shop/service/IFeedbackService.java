package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Feedback;
import com.fresh.manager.pojo.shop.User;

public interface IFeedbackService {
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * @param feedback
	 */
	void updateByPrimaryKeySelective(Feedback feedback);

	/**
	 * 插入
	 * @param feedback
	 */
	void insertSelective(Feedback feedback);

	/**
	 * 查询总数
	 * @param feedback
	 * @return
	 */
	long queryTotal(Feedback feedback);

	/**
	 * 查询列表
	 * @param feedback
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Feedback> queryList(Feedback feedback, Integer pageNum, Integer pageSize);

	/**
	 * 根据主键查询
	 * @param FeedbackId
	 * @return
	 */
	Feedback queryById(Integer FeedbackId);
	/**
	 * 插入for API
	 * @param loginUser
	 * @param feedback
	 */
	void saveForApi(User loginUser, Feedback feedback);

}
