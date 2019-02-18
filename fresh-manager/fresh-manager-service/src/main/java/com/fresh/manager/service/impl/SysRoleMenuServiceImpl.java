package com.fresh.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.SysRoleMenuMapper;
import com.fresh.manager.pojo.SysRoleMenuExample;
import com.fresh.manager.pojo.SysRoleMenuExample.Criteria;
import com.fresh.manager.service.ISysRoleMenuService;


/**
 * 角色与菜单对应关系
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        if (menuIdList.size() == 0) {
            return;
        }
        //先删除角色与菜单关系
        SysRoleMenuExample se = new SysRoleMenuExample();
        Criteria c1 =se.createCriteria();
        c1.andRoleIdEqualTo(roleId);
        sysRoleMenuMapper.deleteByExample(se);

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        
        sysRoleMenuMapper.save(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return sysRoleMenuMapper.queryMenuIdList(roleId);
    }

}
