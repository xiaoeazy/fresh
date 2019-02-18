package com.fresh.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.SysUserRoleMapper;
import com.fresh.manager.pojo.SysUserRoleExample;
import com.fresh.manager.pojo.SysUserRoleExample.Criteria;
import com.fresh.manager.service.ISysUserRoleService;


/**
 * 用户与角色对应关系
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl implements ISysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (roleIdList.size() == 0) {
            return;
        }

        //先删除用户与角色关系
        ((ISysUserRoleService) AopContext.currentProxy()).delete(userId);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        sysUserRoleMapper.save(map);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return sysUserRoleMapper.queryRoleIdList(userId);
    }

    @Override
    public int delete(Long userId) {
    	SysUserRoleExample s1  = new SysUserRoleExample();
    	Criteria c1 = s1.createCriteria();
    	c1.andUserIdEqualTo(userId);
        return sysUserRoleMapper.deleteByExample(s1);
    }
}
