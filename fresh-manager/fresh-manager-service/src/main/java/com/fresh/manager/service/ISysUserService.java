package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysUser;


/**
 * 系统用户
 *
 */
public interface ISysUserService {
	
	 /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
	List<String> queryAllPerms(Long userId);
	/**
	 * 查询用户的所有菜单ID
	 * @param userId
	 * @return
	 */
	List<Long> queryAllMenuId(Long userId);
	/**
	 * 根据主键查询
	 * @param userId
	 * @return
	 */
	SysUser queryById(Long userId);
	/**
	 * 根据用户名去查找 不是模糊查询
	 * @param username
	 * @return
	 */
	SysUser queryByUserName(String username);
	/**
	 * 查询总数
	 * @param sysUser
	 * @return
	 */
	long queryTotal(SysUser sysUser);
	/**
	 * 查询
	 * @param sysUser
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysUser> queryList(SysUser sysUser, Integer pageNum, Integer pageSize);
	/**
	 * 插入
	 * @param user
	 */
	void save(SysUser user);
	/**
	 * 更新
	 * @param user
	 */
	void update(SysUser user);
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Long> values);
	/**
	 * 更新用户名密码
	 * @param userId
	 * @param password
	 * @param newPassword
	 * @return
	 */
	int updatePassword(Long userId, String password, String newPassword);

}
