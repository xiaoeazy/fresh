package com.fresh.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.constant.Constant;
import com.fresh.common.exception.RRException;
import com.fresh.manager.mapper.SysRoleDeptMapper;
import com.fresh.manager.mapper.SysRoleMapper;
import com.fresh.manager.mapper.SysRoleMenuMapper;
import com.fresh.manager.mapper.SysUserRoleMapper;
import com.fresh.manager.pojo.SysRole;
import com.fresh.manager.pojo.SysRoleDeptExample;
import com.fresh.manager.pojo.SysRoleExample;
import com.fresh.manager.pojo.SysRoleExample.Criteria;
import com.fresh.manager.pojo.SysRoleMenuExample;
import com.fresh.manager.pojo.SysUserRoleExample;
import com.fresh.manager.service.ISysRoleDeptService;
import com.fresh.manager.service.ISysRoleMenuService;
import com.fresh.manager.service.ISysRoleService;
import com.fresh.manager.service.ISysUserService;
import com.github.pagehelper.PageHelper;


/**
 * 角色
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleDeptService sysRoleDeptService;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysRole queryById(Long roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<SysRole> queryList(SysRole role,Integer pageNum ,Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
        return sysRoleMapper.queryList(role);
    }

    @Override
    public long queryTotal(SysRole role) {
    	SysRoleExample se = new SysRoleExample();
    	Criteria criteria = se.createCriteria();
    	if(role.getRoleName()!=null){
    		criteria.andRoleNameLike("%"+role.getRoleName()+"%");
    	}
    	if(role.getCreateUserId()!=null){
    		criteria.andCreateUserIdEqualTo(role.getCreateUserId());
    	}
        return sysRoleMapper.countByExample(se);
    }

    @Override
    public void save(SysRole role) {
        role.setCreateTime(new Date());
        sysRoleMapper.insertSelective(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }

    @Override
    public void update(SysRole role) {
        sysRoleMapper.updateByPrimaryKey(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }

    @Override
    public void deleteBatch(List<Long> values) {
    	SysRoleExample s1 = new SysRoleExample();
    	Criteria c1 =s1.createCriteria();
    	c1.andRoleIdIn(values);
        sysRoleMapper.deleteByExample(s1);
        
        SysRoleMenuExample s2 = new SysRoleMenuExample();
        com.fresh.manager.pojo.SysRoleMenuExample.Criteria c2 =s2.createCriteria();
        c2.andRoleIdIn(values);
        sysRoleMenuMapper.deleteByExample(s2);
        
        SysRoleDeptExample s3 = new SysRoleDeptExample();
        com.fresh.manager.pojo.SysRoleDeptExample.Criteria c3 =s3.createCriteria();
        c3.andRoleIdIn(values);
        sysRoleDeptMapper.deleteByExample(s3);
        
        
        SysUserRoleExample  s4 = new SysUserRoleExample();
        com.fresh.manager.pojo.SysUserRoleExample.Criteria c4 =s4.createCriteria();
        c4.andRoleIdIn(values);
        sysUserRoleMapper.deleteByExample(s4);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return sysRoleMapper.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRole role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new RRException("新增角色的权限，已超出你的权限范围");
        }
    }

    
}
