package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.GoodsgalleryMapper;
import com.fresh.manager.pojo.shop.Goodsgallery;
import com.fresh.manager.pojo.shop.GoodsgalleryExample;
import com.fresh.manager.pojo.shop.GoodsgalleryExample.Criteria;
import com.fresh.manager.shop.service.IGoodsGalleryService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsGalleryServiceImpl implements IGoodsGalleryService {
    @Autowired
    private GoodsgalleryMapper goodsgalleryMapper;

    @Override
    public Goodsgallery queryById(Integer GoodsgalleryId) {
        return goodsgalleryMapper.selectByPrimaryKey(GoodsgalleryId);
    }
    @Override
   	public List<Goodsgallery> queryList(Goodsgallery goodsgallery, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
//   		GoodsgalleryExample ge = new GoodsgalleryExample();
//   		Criteria c =  ge.createCriteria();
//       	if(!StringUtils.isEmpty(Goodsgallery.getGoodsgalleryname())){
//           	c.andGoodsgallerynameLike("%"+Goodsgallery.getGoodsgalleryname()+"%");
//       	}
       	List<Goodsgallery> list = goodsgalleryMapper.queryList(goodsgallery);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Goodsgallery goodsgallery) {
		return goodsgalleryMapper.queryTotal(goodsgallery);
    }
    
    
    @Override
    public void insertSelective(Goodsgallery goodsgallery) {
    	goodsgalleryMapper.insertSelective(goodsgallery);
    }

    @Override
    public void updateByPrimaryKeySelective(Goodsgallery goodsgallery) {
    	goodsgalleryMapper.updateByPrimaryKeySelective(goodsgallery);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	GoodsgalleryExample ge = new GoodsgalleryExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	goodsgalleryMapper.deleteByExample(ge);
    	
    }

}
