package com.fresh.manager.service;


import java.util.List;

import com.fresh.manager.pojo.SysRegion;

/**
 * Service接口
 *
 */
public interface ISysRegionService {
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(List<Integer> ids);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * 更新
	 * @param region
	 * @return
	 */
	int updateByPrimaryKeySelective(SysRegion region);
	/**
	 * 插入
	 * @param region
	 * @return
	 */
	int insertSelective(SysRegion region);
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	SysRegion queryById(Integer id);
	/**
	 * 查询总数
	 * @param region
	 * @return
	 */
	int queryTotal(SysRegion region);
	/**
	 * 查询
	 * @param region
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysRegion> queryList(SysRegion region, int pageNum, int pageSize);

   
}
