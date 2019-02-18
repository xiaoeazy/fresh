package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.SpecificationMapper;
import com.fresh.manager.pojo.shop.Specification;
import com.fresh.manager.pojo.shop.SpecificationExample;
import com.fresh.manager.pojo.shop.SpecificationExample.Criteria;
import com.fresh.manager.shop.service.ISpecificationService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class SpecificationServiceImpl implements ISpecificationService {
	@Autowired
	private SpecificationMapper specificationMapper;
	
	 @Override
	    public Specification queryById(Integer SpecificationId) {
	        return specificationMapper.selectByPrimaryKey(SpecificationId);
	    }
	    @Override
	   	public List<Specification> queryList(Specification specification, Integer pageNum, Integer pageSize) {
	    	if(pageNum!=null&&pageSize!=null)
	    		PageHelper.startPage(pageNum, pageSize);
	   		SpecificationExample ge = new SpecificationExample();
	   		Criteria c =  ge.createCriteria();
	       	List<Specification> list = specificationMapper.selectByExample(ge);
	           
	        return list;
	   	}
	    
	    @Override
	    public long queryTotal(Specification specification) {
	        SpecificationExample ge = new SpecificationExample();
	   		Criteria c =  ge.createCriteria();
			return specificationMapper.countByExample(ge);
	    }
	    
	    
	    @Override
	    public void insertSelective(Specification specification) {
	    	specificationMapper.insertSelective(specification);
	    }

	    @Override
	    public void updateByPrimaryKeySelective(Specification specification) {
	    	specificationMapper.updateByPrimaryKeySelective(specification);
	    }
	    
	    @Override
	    public void deleteBatch(List<Integer> values) {
	    	SpecificationExample ge = new SpecificationExample();
	    	Criteria c =  ge.createCriteria();
	    	c.andIdIn(values);
	    	specificationMapper.deleteByExample(ge);
	    	
	    }

	
}
