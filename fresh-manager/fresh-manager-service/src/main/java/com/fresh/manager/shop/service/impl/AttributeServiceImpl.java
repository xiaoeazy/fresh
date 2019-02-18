package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.AttributeMapper;
import com.fresh.manager.pojo.shop.Attribute;
import com.fresh.manager.pojo.shop.AttributeExample;
import com.fresh.manager.pojo.shop.AttributeExample.Criteria;
import com.fresh.manager.shop.service.IAttributeService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class AttributeServiceImpl implements IAttributeService {
		@Autowired
		private AttributeMapper attributeMapper;
	
	 	@Override
	    public Attribute queryById(Integer id) {
	        return attributeMapper.selectByPrimaryKey(id);
	    }
	    @Override
	   	public List<Attribute> queryList(Attribute attribute, Integer pageNum, Integer pageSize) {
	    	if(pageNum!=null&&pageSize!=null)
	    		PageHelper.startPage(pageNum, pageSize);
	       	List<Attribute> list = attributeMapper.queryList(attribute);
	           
	        return list;
	   	}
	    

	    @Override
	    public long queryTotal(Attribute attribute) {
			return attributeMapper.queryTotal(attribute);
	    }
	    
	    
	    @Override
	    public void insertSelective(Attribute attribute) {
	    	attribute.setValue("");
	    	attributeMapper.insertSelective(attribute);
	    }

	    @Override
	    public void updateByPrimaryKeySelective(Attribute attribute) {
	    	attributeMapper.updateByPrimaryKeySelective(attribute);
	    }
	    
	    @Override
	    public void deleteBatch(List<Integer> values) {
	    	AttributeExample ge = new AttributeExample();
	    	Criteria c =  ge.createCriteria();
	    	c.andIdIn(values);
	    	attributeMapper.deleteByExample(ge);
	    }
}
