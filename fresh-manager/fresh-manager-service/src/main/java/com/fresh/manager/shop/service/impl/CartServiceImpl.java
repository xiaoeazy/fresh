package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.CartMapper;
import com.fresh.manager.pojo.shop.Cart;
import com.fresh.manager.pojo.shop.CartExample;
import com.fresh.manager.pojo.shop.CartExample.Criteria;
import com.fresh.manager.shop.service.ICartService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements ICartService {
	@Autowired
	private CartMapper cartMapper;
	
	 @Override
	    public Cart queryById(Integer id) {
	        return cartMapper.selectByPrimaryKey(id);
	    }
	    
	    @Override
	   	public List<Cart> queryList(Cart cart, Integer pageNum, Integer pageSize) {
	    	if(pageNum!=null&&pageSize!=null)
	    		PageHelper.startPage(pageNum, pageSize);
	       	List<Cart> list = cartMapper.queryList(cart);
	           
	        return list;
	   	}
	    

	    @Override
	    public long queryTotal(Cart cart) {
	    	CartExample ge = new CartExample();
	   		Criteria c =  ge.createCriteria();
	   		if(cart.getUserId()!=null){
	           	c.andUserIdEqualTo(cart.getUserId());
	       	}
			return cartMapper.countByExample(ge);
	    }
	    
	    
	    @Override
	    public void insertSelective(Cart cart) {
	    	cartMapper.insertSelective(cart);
	    }

	    @Override
	    public void updateByPrimaryKeySelective(Cart cart) {
	    	cartMapper.updateByPrimaryKeySelective(cart);
	    }
	    
	    @Override
	    public void deleteBatch(List<Integer> values) {
	    	CartExample ge = new CartExample();
	    	Criteria c =  ge.createCriteria();
	    	c.andIdIn(values);
	    	cartMapper.deleteByExample(ge);
	    }
	
}
