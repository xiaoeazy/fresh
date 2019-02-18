package com.fresh.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.constant.Constant.MenuType;
import com.fresh.manager.mapper.SysMenuMapper;
import com.fresh.manager.mapper.SysRoleMenuMapper;
import com.fresh.manager.pojo.SysMenu;
import com.fresh.manager.pojo.SysMenuExample;
import com.fresh.manager.pojo.SysMenuExample.Criteria;
import com.fresh.manager.pojo.SysRoleMenuExample;
import com.fresh.manager.service.ISysMenuService;
import com.fresh.manager.service.ISysUserService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl implements ISysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private SysRoleMenuMapper SysRoleMenuMapper;
	
	@Override
	public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenu> menuList = sysMenuMapper.queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenu> userMenuList = new ArrayList<>();
		for(SysMenu menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenu> queryNotButtonList() {
		return sysMenuMapper.queryNotButtonList();
	}

	@Override
	public List<SysMenu> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if(userId == 1){
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	@Override
	public SysMenu queryById(Long menuId) {
		return sysMenuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public List<SysMenu> queryList(SysMenu sysMenu, Integer pageNum, Integer pageSize) {
		if(pageNum!=null && pageSize!=null )
			PageHelper.startPage(pageNum, pageSize);
        return sysMenuMapper.queryList(sysMenu);
	}

	@Override
	public int queryTotal(SysMenu sysMenu) {
		return sysMenuMapper.queryTotal(sysMenu);
	}

	@Override
	public int insertSelective(SysMenu menu) {
		return sysMenuMapper.insertSelective(menu);
	}

	@Override
	public int updateByPrimaryKeySelective(SysMenu menu) {
		return sysMenuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void deleteBatch(List<Long> values) {
		SysMenuExample s1  = new SysMenuExample();
		Criteria c1 =s1.createCriteria();
		c1.andMenuIdIn(values);
		
		sysMenuMapper.deleteByExample(s1);
		
		
		SysRoleMenuExample s2 = new SysRoleMenuExample();
		com.fresh.manager.pojo.SysRoleMenuExample.Criteria c2 = s2.createCriteria();
		c2.andMenuIdIn(values);
		
		SysRoleMenuMapper.deleteByExample(s2);
	}
	
	@Override
	public List<SysMenu> queryUserList(Long userId) {
		return sysMenuMapper.queryUserList(userId);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenu> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenu> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList){
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		
		for(SysMenu entity : menuList){
			if(entity.getType() == MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}
