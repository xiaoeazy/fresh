package com.fresh.manager.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.GoodsspecificationMapper;
import com.fresh.manager.pojo.shop.Goodsspecification;
import com.fresh.manager.pojo.shop.GoodsspecificationExample;
import com.fresh.manager.pojo.shop.GoodsspecificationExample.Criteria;
import com.fresh.manager.shop.service.IGoodsSpecificationService;
import com.github.pagehelper.PageHelper;

/**
 * 商品对应规格表值表Service实现类
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsSpecificationServiceImpl implements IGoodsSpecificationService {
    @Autowired
    private GoodsspecificationMapper goodsspecificationMapper;

    @Override
    public Goodsspecification queryById(Integer GoodsspecificationId) {
        return goodsspecificationMapper.selectByPrimaryKey(GoodsspecificationId);
    }
    @Override
   	public List<Goodsspecification> queryList(Goodsspecification goodsspecification, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
//   		GoodsspecificationExample ge = new GoodsspecificationExample();
//   		Criteria c =  ge.createCriteria();
//       	if(!StringUtils.isEmpty(Goodsspecification.getGoodsspecificationname())){
//           	c.andGoodsspecificationnameLike("%"+Goodsspecification.getGoodsspecificationname()+"%");
//       	}
       	List<Goodsspecification> list = goodsspecificationMapper.queryList(goodsspecification);
           
        return list;
   	}
    
    @Override
    public int queryTotal(Goodsspecification goodsspecification) {
		return goodsspecificationMapper.queryTotal(goodsspecification);
    }
    
    
    @Override
    public void insertSelective(Goodsspecification goodsspecification) {
    	goodsspecificationMapper.insertSelective(goodsspecification);
    }

    @Override
    public void updateByPrimaryKeySelective(Goodsspecification goodsspecification) {
    	goodsspecificationMapper.updateByPrimaryKeySelective(goodsspecification);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	GoodsspecificationExample ge = new GoodsspecificationExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	goodsspecificationMapper.deleteByExample(ge);
    	
    }

}
