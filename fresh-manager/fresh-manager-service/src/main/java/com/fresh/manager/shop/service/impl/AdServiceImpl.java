package com.fresh.manager.shop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.AdMapper;
import com.fresh.manager.pojo.shop.Ad;
import com.fresh.manager.pojo.shop.AdExample;
import com.fresh.manager.pojo.shop.AdExample.Criteria;
import com.fresh.manager.shop.service.IAdService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class AdServiceImpl implements IAdService {
    @Autowired
    private AdMapper adMapper;
    @Override
    public Ad queryById(Integer userId) {
        return adMapper.selectByPrimaryKey(userId);
    }
    @Override
   	public List<Ad> queryList(Ad ad, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
//   		AdExample ge = new AdExample();
//   		Criteria c =  ge.createCriteria();
//       	if(!StringUtils.isEmpty(user.getUsername())){
//           	c.andUsernameLike("%"+user.getUsername()+"%");
//       	}
       	List<Ad> list = adMapper.queryList(ad);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Ad ad) {
        AdExample ge = new AdExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(ad.getName())){
           	c.andNameLike("%"+ad.getName()+"%");
       	}
		return adMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Ad ad) {
    	adMapper.insertSelective(ad);
    }

    @Override
    public void updateByPrimaryKeySelective(Ad ad) {
    	adMapper.updateByPrimaryKeySelective(ad);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	AdExample ge = new AdExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	adMapper.deleteByExample(ge);
    }
}
