package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.User;

/**
 * Service接口
 *
 */
public interface IUserService {
	/**
	 * 查询所有
	 * @param User
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<User> queryList(User user, Integer pageNum, Integer pageSize);
	/**
	 * 查询数量
	 * @param user
	 * @return
	 */
	long queryTotal(User user);
	/**
	 * 根据userid获取用户
	 * @param userId
	 * @return
	 */
	User queryById(Integer userId);
	/**
	 * 插入
	 * @param user
	 */
	void insertSelective(User user);
	/**
	 * 更新s
	 * @param user
	 */
	void updateByPrimaryKeySelective(User user);
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Integer> values);
	
	/**
	 * 根据openid去查找用户
	 * @param openid
	 * @return
	 */
	User queryByOpenId(String openid);

   
}
