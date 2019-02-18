package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.CategoryMapper;
import com.fresh.manager.pojo.shop.Category;
import com.fresh.manager.pojo.shop.CategoryExample;
import com.fresh.manager.pojo.shop.CategoryExample.Criteria;
import com.fresh.manager.shop.service.ICategoryService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public Category queryById(Integer userId) {
        return categoryMapper.selectByPrimaryKey(userId);
    }
    @Override
   	public List<Category> queryList(Category category, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
    	CategoryExample ge = new CategoryExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(category.getName())){
           	c.andNameLike("%"+category.getName()+"%");
       	}
       	if(category.getParentId()!=null){
           	c.andParentIdEqualTo(category.getParentId());
       	}
       	List<Category> list = categoryMapper.selectByExample(ge);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Category category) {
    	CategoryExample ge = new CategoryExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(category.getName())){
           	c.andNameLike("%"+category.getName()+"%");
       	}
       	if(category.getParentId()!=null){
           	c.andParentIdEqualTo(category.getParentId());
       	}
		return categoryMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Category category) {
    	  if ("L1".equals(category.getLevel())) {
              category.setParentId(0);
          }
    	categoryMapper.insertSelective(category);
    }

    @Override
    public void updateByPrimaryKeySelective(Category category) {
    	if ("L1".equals(category.getLevel())) {
            category.setParentId(0);
        }
    	categoryMapper.updateByPrimaryKeySelective(category);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	CategoryExample ge = new CategoryExample();
   		Criteria c =  ge.createCriteria();
   		c.andIdIn(values);
   		categoryMapper.deleteByExample(ge);
    }
    
}
