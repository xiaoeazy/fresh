package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysMenu;


/**
 * 菜单管理
 *
 */
public interface ISysMenuService {
	/**
	 * 更新
	 * @param menu
	 * @return
	 */
	int updateByPrimaryKeySelective(SysMenu menu);
	/**
	 * 插入
	 * @param menu
	 * @return
	 */
	int insertSelective(SysMenu menu);
	/**
	 * 根据主键查询
	 * @param menuId
	 * @return
	 */
	SysMenu queryById(Long menuId);
	/**
	 * 获取不包含按钮的菜单列表
	 * @return
	 */
	List<SysMenu> queryNotButtonList();
	/**
	 * 查询
	 * @param sysMenu
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysMenu> queryList(SysMenu sysMenu, Integer pageNum, Integer pageSize);
	/**
	 * 总数
	 * @param sysMenu
	 * @return
	 */
	int queryTotal(SysMenu sysMenu);
	/**
	 * 查询用户的权限列表
	 * @param userId
	 * @return
	 */
	List<SysMenu> queryUserList(Long userId);
	/**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
	List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);
	/**
	 * 根据用户名ID查询用户菜单
	 * @param userId
	 * @return
	 */
	List<SysMenu> getUserMenuList(Long userId);
	/**
	 * 批量删除
	 * @param values
	 */
	void deleteBatch(List<Long> values);

}
