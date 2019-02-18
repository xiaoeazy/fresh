package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysRole;


/**
 * 角色
 */
public interface ISysRoleService {
	
	/**
	 * 根据主键查询
	 * @param roleId
	 * @return
	 */
	SysRole queryById(Long roleId);
	/**
	 * 查询
	 * @param role
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysRole> queryList(SysRole role, Integer pageNum, Integer pageSize);
	/**
	 * size
	 * @param role
	 * @return
	 */
	long queryTotal(SysRole role);
	/**
	 * 保存
	 * @param role
	 */
	void save(SysRole role);
	/**
	 * 查询用户创建的角色ID列表 
	 * @param createUserId
	 * @return
	 */
	List<Long> queryRoleIdList(Long createUserId);
	/**
	 * 更新
	 * @param role
	 */
	void update(SysRole role);
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Long> values);


    
}
