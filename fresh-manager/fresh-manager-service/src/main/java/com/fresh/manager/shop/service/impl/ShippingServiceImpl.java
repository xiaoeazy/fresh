package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.ShippingMapper;
import com.fresh.manager.pojo.shop.Shipping;
import com.fresh.manager.pojo.shop.ShippingExample;
import com.fresh.manager.pojo.shop.ShippingExample.Criteria;
import com.fresh.manager.shop.service.IShippingService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    
    @Override
    public Shipping queryById(Integer id) {
        return shippingMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Shipping> queryList(Shipping shipping, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
		ShippingExample ge = new ShippingExample();
		Criteria c =  ge.createCriteria();
	   	if(!StringUtils.isEmpty(shipping.getName())){
	       	c.andNameLike("%"+shipping.getName()+"%");
	   	}
       	List<Shipping> list = shippingMapper.selectByExample(ge);
        return list;
   	}
    
    @Override
    public long queryTotal(Shipping shipping) {
   		ShippingExample ge = new ShippingExample();
		Criteria c =  ge.createCriteria();
	   	if(!StringUtils.isEmpty(shipping.getName())){
	       	c.andNameLike("%"+shipping.getName()+"%");
	   	}
		return shippingMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Shipping shipping) {
    	shippingMapper.insertSelective(shipping);
    }

    @Override
    public void updateByPrimaryKeySelective(Shipping shipping) {
    	shippingMapper.updateByPrimaryKeySelective(shipping);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	ShippingExample ge = new ShippingExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	shippingMapper.deleteByExample(ge);
    	
    }


}
