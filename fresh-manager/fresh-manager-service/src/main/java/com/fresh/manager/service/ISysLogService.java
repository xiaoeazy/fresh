package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysLog;

/**
 * 系统日志
 *
 */
public interface ISysLogService {
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	SysLog queryById(Long id);
	/**
	 * 查询
	 * @param sysLog
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysLog> queryList(SysLog sysLog, int pageNum, int pageSize);
	/**
	 * 查询size
	 * @param sysLog
	 * @return
	 */
	long queryTotal(SysLog sysLog);
	/**
	 * 插入
	 * @param sysLog
	 * @return
	 */
	int insertSelective(SysLog sysLog);
	/**
	 * 更新
	 * @param sysLog
	 * @return
	 */
	int updateByPrimaryKeySelective(SysLog sysLog);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(List<Long> ids);

}
