package com.fresh.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.SysDeptMapper;
import com.fresh.manager.pojo.SysDept;
import com.fresh.manager.pojo.SysDeptExample;
import com.fresh.manager.pojo.SysDeptExample.Criteria;
import com.fresh.manager.service.ISysDeptService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl implements ISysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
	public SysDept queryById(Long id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

    @Override
	public List<SysDept> queryList(SysDept sysDept, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
		SysDeptExample ge = new SysDeptExample();
		 Criteria c = ge.createCriteria();
    	if(sysDept.getDeptId()!=null){
        	c.andDeptIdEqualTo(sysDept.getDeptId());
    	}
    	if(sysDept.getDeptFilter()!=null){
        	c.andDeptIdIn(sysDept.getDeptFilter());
    	}
    	if(!StringUtils.isEmpty(sysDept.getName())){
        	c.andNameEqualTo(sysDept.getName());
    	}
    	if(sysDept.getDelFlag()!=null){
        	c.andDelFlagEqualTo(sysDept.getDelFlag());
    	}
    	if(sysDept.getOrderNum()!=null){
        	c.andOrderNumEqualTo(sysDept.getOrderNum());
    	}
    	if(sysDept.getParentId()!=null){
        	c.andParentIdEqualTo(sysDept.getParentId());
    	}
        return sysDeptMapper.selectByExample(ge);
	}

    
    @Override
	public int insertSelective(SysDept sysDept) {
		return sysDeptMapper.insertSelective(sysDept);
	}

    @Override
	public int updateByPrimaryKeySelective(SysDept sysDept) {
		return sysDeptMapper.updateByPrimaryKeySelective(sysDept);
	}

    @Override
    public void deleteByPrimaryKey(Long deptId) {
        sysDeptMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public List<Long> queryDetpIdList(Long parentId) {
        return sysDeptMapper.queryDetpIdList(parentId);
    }

    @Override
    public List<Long> getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList =  ((ISysDeptService) AopContext.currentProxy()).queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        //添加本部门
        deptIdList.add(deptId);

//        String deptFilter = QiNiuUtils.join(deptIdList, ",");
//        return deptFilter;
        
        return deptIdList;
    }

    /**
     * 递归
     */
    public void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }

   
}
