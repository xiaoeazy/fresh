package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysSmsLog;
import com.fresh.manager.pojo.shop.User;

/**
 * 发送短信日志Service
 */
public interface ISysSmsLogService {
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	SysSmsLog queryById(String id);
	/**
	 * 查询数量
	 * @param smsLog
	 * @return
	 */
	long queryTotal(SysSmsLog smsLog);
	/**
	 * 查询内容
	 * @param smsLog
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysSmsLog> queryList(SysSmsLog smsLog, Integer pageNum, Integer pageSize);
	/**
	 * 更新
	 * @param smsLog
	 * @return
	 */
	int updateByPrimaryKey(SysSmsLog smsLog);
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(List<String> ids);
	/**
	 * 插入
	 * @param smsLog
	 * @return
	 */
	int insertSelective(SysSmsLog smsLog);
	/**
	 * 发送短消息
	 * @param loginUser
	 * @param smsLog
	 * @return
	 */
	SysSmsLog sendSms(User loginUser,SysSmsLog smsLog);

}
