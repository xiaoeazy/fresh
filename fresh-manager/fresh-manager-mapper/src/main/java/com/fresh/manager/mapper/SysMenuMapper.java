package com.fresh.manager.mapper;

import com.fresh.manager.pojo.SysMenu;
import com.fresh.manager.pojo.SysMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    long countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    
    
    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);
    

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();
    
    
    List<SysMenu> queryList(SysMenu record);
    
    int queryTotal(SysMenu record);
    
    /**
     * 查询用户的权限列表
     */
    List<SysMenu> queryUserList(Long userId);
}