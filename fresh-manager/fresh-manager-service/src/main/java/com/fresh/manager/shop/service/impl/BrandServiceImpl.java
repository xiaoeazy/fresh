package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.BrandMapper;
import com.fresh.manager.pojo.shop.Brand;
import com.fresh.manager.pojo.shop.BrandExample;
import com.fresh.manager.pojo.shop.BrandExample.Criteria;
import com.fresh.manager.shop.service.IBrandService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;

 	@Override
    public Brand queryById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Brand> queryList(Brand brand, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
		BrandExample ge = new BrandExample();
		Criteria c =  ge.createCriteria();
		if(!StringUtils.isEmpty(brand.getName())){
			c.andNameLike("%"+brand.getName()+"%");
		}
       	List<Brand> list = brandMapper.selectByExample(ge);
        return list;
   	}
    

    @Override
    public long queryTotal(Brand brand) {
    	BrandExample ge = new BrandExample();
		Criteria c =  ge.createCriteria();
		if(!StringUtils.isEmpty(brand.getName())){
			c.andNameLike("%"+brand.getName()+"%");
		}
		return brandMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Brand brand) {
    	brandMapper.insertSelective(brand);
    }

    @Override
    public void updateByPrimaryKeySelective(Brand brand) {
    	brandMapper.updateByPrimaryKeySelective(brand);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	BrandExample ge = new BrandExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	brandMapper.deleteByExample(ge);
    }
}
