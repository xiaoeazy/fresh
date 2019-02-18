package com.fresh.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.SysRoleDeptMapper;
import com.fresh.manager.pojo.SysRoleDeptExample;
import com.fresh.manager.pojo.SysRoleDeptExample.Criteria;
import com.fresh.manager.service.ISysRoleDeptService;


/**
 * 角色与部门对应关系
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleDeptServiceImpl implements ISysRoleDeptService {
    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色与菜单关系
    	SysRoleDeptExample se = new SysRoleDeptExample();
    	Criteria criteria = se.createCriteria();
    	criteria.andRoleIdEqualTo(roleId);
        sysRoleDeptMapper.deleteByExample(se);

        if (deptIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("deptIdList", deptIdList);
        sysRoleDeptMapper.save(map);
    }

    @Override
    public List<Long> queryDeptIdList(Long roleId) {
        return sysRoleDeptMapper.queryDeptIdList(roleId);
    }

    @Override
    public List<Long> queryDeptIdListByUserId(Long userId) {
        return sysRoleDeptMapper.queryDeptIdListByUserId(userId);
    }
}
