package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.AdpositionMapper;
import com.fresh.manager.pojo.shop.Adposition;
import com.fresh.manager.pojo.shop.AdpositionExample;
import com.fresh.manager.pojo.shop.AdpositionExample.Criteria;
import com.fresh.manager.shop.service.IAdPositionService;
import com.github.pagehelper.PageHelper;



@Service
@Transactional(rollbackFor = Exception.class)
public class AdPositionServiceImpl implements IAdPositionService {
    @Autowired
    private AdpositionMapper AdpositionMapper;


    @Override
    public Adposition queryById(Integer AdpositionId) {
        return AdpositionMapper.selectByPrimaryKey(AdpositionId);
    }
    @Override
   	public List<Adposition> queryList(Adposition adposition, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
    	AdpositionExample ge = new AdpositionExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(adposition.getName())){
           	c.andNameLike("%"+adposition.getName()+"%");
       	}
       	List<Adposition> list = AdpositionMapper.selectByExample(ge);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Adposition adposition) {
        AdpositionExample ge = new AdpositionExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(adposition.getName())){
           	c.andNameLike("%"+adposition.getName()+"%");
       	}
		return AdpositionMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Adposition adposition) {
    	AdpositionMapper.insertSelective(adposition);
    }

    @Override
    public void updateByPrimaryKeySelective(Adposition adposition) {
    	AdpositionMapper.updateByPrimaryKeySelective(adposition);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	AdpositionExample ge = new AdpositionExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	AdpositionMapper.deleteByExample(ge);
    }
}
