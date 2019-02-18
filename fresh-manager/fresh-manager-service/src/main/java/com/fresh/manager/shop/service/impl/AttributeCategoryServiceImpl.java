package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.AttributeMapper;
import com.fresh.manager.mapper.shop.AttributecategoryMapper;
import com.fresh.manager.pojo.shop.AttributeExample;
import com.fresh.manager.pojo.shop.Attributecategory;
import com.fresh.manager.pojo.shop.AttributecategoryExample;
import com.fresh.manager.pojo.shop.AttributecategoryExample.Criteria;
import com.fresh.manager.shop.service.IAttributeCategoryService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class AttributeCategoryServiceImpl implements IAttributeCategoryService {
    @Autowired
    private AttributecategoryMapper attributecategoryMapper;
    @Autowired
    private AttributeMapper attributeMapper;
    
    @Override
    public Attributecategory queryById(Integer id) {
        return attributecategoryMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Attributecategory> queryList(Attributecategory attributecategory, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
   		AttributecategoryExample ge = new AttributecategoryExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(attributecategory.getName())){
           	c.andNameLike("%"+attributecategory.getName()+"%");
       	}
       	if(attributecategory.getEnabled()!=null){
           	c.andEnabledEqualTo(attributecategory.getEnabled());
       	}
       	List<Attributecategory> list = attributecategoryMapper.selectByExample(ge);
           
        return list;
   	}
    

    @Override
    public long queryTotal(Attributecategory attributecategory) {
    	AttributecategoryExample ge = new AttributecategoryExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(attributecategory.getName())){
           	c.andNameLike("%"+attributecategory.getName()+"%");
       	}
       	if(attributecategory.getEnabled()!=null){
           	c.andEnabledEqualTo(attributecategory.getEnabled());
       	}
		return attributecategoryMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Attributecategory attributecategory) {
    	attributecategoryMapper.insertSelective(attributecategory);
    }

    @Override
    public void updateByPrimaryKeySelective(Attributecategory attributecategory) {
    	attributecategoryMapper.updateByPrimaryKeySelective(attributecategory);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	AttributecategoryExample ge = new AttributecategoryExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	attributecategoryMapper.deleteByExample(ge);
    	
    	AttributeExample ge2 = new AttributeExample();
    	com.fresh.manager.pojo.shop.AttributeExample.Criteria c2 =  ge2.createCriteria();
    	c2.andAttributeCategoryIdIn(values);
    	attributeMapper.deleteByExample(ge2);
    }
}
