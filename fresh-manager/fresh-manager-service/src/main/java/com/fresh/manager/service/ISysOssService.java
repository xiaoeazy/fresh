package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysOss;

/**
 * 文件上传Service
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-25 12:13:26
 */
public interface ISysOssService {
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	SysOss queryById(Long id);
	/**
	 * 查询
	 * @param sysOss
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysOss> queryList(SysOss sysOss, Integer pageNum, Integer pageSize);
	/**
	 * 查询总数
	 * @param sysOss
	 * @return
	 */
	long queryTotal(SysOss sysOss);
	/**
	 * 插入
	 * @param sysOss
	 * @return
	 */
	int insertSelective(SysOss sysOss);
	/**
	 * 更新
	 * @param sysOss
	 * @return
	 */
	int updateByPrimaryKeySelective(SysOss sysOss);
	/**
	 * 根据主键删除
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
