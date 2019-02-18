package com.fresh.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.constant.Constant;
import com.fresh.common.exception.RRException;
import com.fresh.manager.mapper.SysUserMapper;
import com.fresh.manager.mapper.SysUserRoleMapper;
import com.fresh.manager.pojo.SysUser;
import com.fresh.manager.pojo.SysUserExample;
import com.fresh.manager.pojo.SysUserExample.Criteria;
import com.fresh.manager.pojo.SysUserRoleExample;
import com.fresh.manager.service.ISysRoleService;
import com.fresh.manager.service.ISysUserRoleService;
import com.fresh.manager.service.ISysUserService;
import com.github.pagehelper.PageHelper;


/**
 * 系统用户
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUser queryByUserName(String username) {
    	SysUserExample se = new SysUserExample();
    	Criteria c1 =se.createCriteria();
    	c1.andUsernameEqualTo(username);
        List<SysUser> list = sysUserMapper.selectByExample(se);
        if(list.size()!=0)
        	return list.get(0);
        else
        	return null;
    }

    @Override
    public SysUser queryById(Long userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysUser> queryList(SysUser sysUser, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
        return sysUserMapper.queryList(sysUser);
    }

    @Override
    public long queryTotal(SysUser sysUser) {
    	SysUserExample se = new SysUserExample();
    	Criteria c1 =se.createCriteria();
    	if(sysUser.getUsername()!=null)
    		c1.andUsernameLike("%"+sysUser.getUsername()+"%");
    	if(sysUser.getStatus()!=null)
    		c1.andStatusEqualTo(sysUser.getStatus());
    	if(sysUser.getStatus()!=null)
    		c1.andStatusEqualTo(sysUser.getStatus());
        return sysUserMapper.countByExample(se);
    }

    @Override
    public void save(SysUser user) {
        user.setCreateTime(new Date());
        //sha256加密
        user.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
        sysUserMapper.insertSelective(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(new Sha256Hash(Constant.DEFAULT_PASS_WORD).toHex());
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sysUserMapper.updateByPrimaryKeySelective(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(List<Long> values) {
    	SysUserExample s1 = new SysUserExample();
    	Criteria c1 =s1.createCriteria();
    	c1.andUserIdIn(values);
    	sysUserMapper.deleteByExample(s1);
    	 
    	 
    	SysUserRoleExample s2 = new SysUserRoleExample();
    	com.fresh.manager.pojo.SysUserRoleExample.Criteria c2 =s2.createCriteria();
    	c2.andUserIdIn(values);
    	sysUserRoleMapper.deleteByExample(s2);
       
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        SysUser record = new SysUser();
        record.setPassword(newPassword);
        
        SysUserExample s1 = new SysUserExample();
    	Criteria c1 =s1.createCriteria();
    	c1.andUserIdEqualTo(userId);
    	c1.andPasswordEqualTo(password);
        
    	
        return sysUserMapper.updateByExampleSelective(record, s1);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUser user) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new RRException("新增用户所选角色，不是本人创建");
        }
    }


}
