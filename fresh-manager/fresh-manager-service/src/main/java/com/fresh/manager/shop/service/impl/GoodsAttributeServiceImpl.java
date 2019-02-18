package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.GoodsattributeMapper;
import com.fresh.manager.pojo.shop.Goodsattribute;
import com.fresh.manager.pojo.shop.GoodsattributeExample;
import com.fresh.manager.pojo.shop.GoodsattributeExample.Criteria;
import com.fresh.manager.shop.service.IGoodsAttributeService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsAttributeServiceImpl implements IGoodsAttributeService {
	@Autowired
	private GoodsattributeMapper goodsattributeMapper;
	
	 @Override
	    public Goodsattribute queryById(Integer id) {
	        return goodsattributeMapper.selectByPrimaryKey(id);
	    }
	    @Override
	   	public List<Goodsattribute> queryList(Goodsattribute goodsattribute, Integer pageNum, Integer pageSize) {
	    	if(pageNum!=null&&pageSize!=null)
	    		PageHelper.startPage(pageNum, pageSize);
	    	GoodsattributeExample ge = new GoodsattributeExample();
	   		Criteria c =  ge.createCriteria();
	       	List<Goodsattribute> list = goodsattributeMapper.selectByExample(ge);
	        return list;
	   	}
	    
	    @Override
	    public long queryTotal(Goodsattribute goodsattribute) {
	    	GoodsattributeExample ge = new GoodsattributeExample();
	   		Criteria c =  ge.createCriteria();
			return goodsattributeMapper.countByExample(ge);
	    }
	    
	    
	    @Override
	    public void insertSelective(Goodsattribute goodsattribute) {
	    	goodsattributeMapper.insertSelective(goodsattribute);
	    }

	    @Override
	    public void updateByPrimaryKeySelective(Goodsattribute goodsattribute) {
	    	goodsattributeMapper.updateByPrimaryKeySelective(goodsattribute);
	    }
	    
	    @Override
	    public void deleteBatch(List<Integer> values) {
	    	GoodsattributeExample ge = new GoodsattributeExample();
	    	Criteria c =  ge.createCriteria();
	    	c.andIdIn(values);
	    	goodsattributeMapper.deleteByExample(ge);
	    }
	
}
