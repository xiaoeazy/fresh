package com.fresh.manager.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.OrdergoodsMapper;
import com.fresh.manager.pojo.shop.Ordergoods;
import com.fresh.manager.pojo.shop.OrdergoodsExample;
import com.fresh.manager.pojo.shop.OrdergoodsExample.Criteria;
import com.fresh.manager.shop.service.IOrderGoodsService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderGoodsServiceImpl implements IOrderGoodsService {
	@Autowired
	private OrdergoodsMapper ordergoodsMapper;
	
	
	@Override
	public List<Ordergoods> queryByOrderId(Integer orderId){
		OrdergoodsExample og = new OrdergoodsExample();
		Criteria c = og.createCriteria();
		c.andOrderIdEqualTo(orderId);
		List<Ordergoods> list = ordergoodsMapper.selectByExample(og);
		return list;
	}
    @Override
    public Ordergoods queryById(Integer id) {
        return ordergoodsMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Ordergoods> queryList(Ordergoods ordergoods, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
    	OrdergoodsExample ge = new OrdergoodsExample();
   		Criteria c =  ge.createCriteria();
       	if(ordergoods.getOrderId()!=null){
           	c.andOrderIdEqualTo(ordergoods.getOrderId());
       	}
       	List<Ordergoods> list = ordergoodsMapper.selectByExample(ge);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Ordergoods ordergoods) {
    	OrdergoodsExample ge = new OrdergoodsExample();
   		Criteria c =  ge.createCriteria();
       	if(ordergoods.getOrderId()!=null){
           	c.andOrderIdEqualTo(ordergoods.getOrderId());
       	}
		return ordergoodsMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Ordergoods ordergoods) {
    	ordergoodsMapper.insertSelective(ordergoods);
    }

    @Override
    public void updateByPrimaryKeySelective(Ordergoods ordergoods) {
    	ordergoodsMapper.updateByPrimaryKeySelective(ordergoods);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	OrdergoodsExample ge = new OrdergoodsExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	ordergoodsMapper.deleteByExample(ge);
    	
    }
}
