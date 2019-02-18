package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.GoodsissueMapper;
import com.fresh.manager.pojo.shop.Goodsissue;
import com.fresh.manager.pojo.shop.GoodsissueExample;
import com.fresh.manager.pojo.shop.GoodsissueExample.Criteria;
import com.fresh.manager.shop.service.IGoodsIssueService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsIssueServiceImpl implements IGoodsIssueService {
    @Autowired
    private GoodsissueMapper goodsissueMapper;


    @Override
    public Goodsissue queryById(Integer id) {
        return goodsissueMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Goodsissue> queryList(Goodsissue goodsissue, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
    	GoodsissueExample ge = new GoodsissueExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(goodsissue.getQuestion())){
           	c.andQuestionLike("%"+goodsissue.getQuestion()+"%");
       	}
       	List<Goodsissue> list = goodsissueMapper.selectByExample(ge);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Goodsissue goodsissue) {
    	GoodsissueExample ge = new GoodsissueExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(goodsissue.getQuestion())){
           	c.andQuestionLike("%"+goodsissue.getQuestion()+"%");
       	}
		return goodsissueMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Goodsissue goodsissue) {
    	goodsissueMapper.insertSelective(goodsissue);
    }

    @Override
    public void updateByPrimaryKeySelective(Goodsissue goodsissue) {
    	goodsissueMapper.updateByPrimaryKeySelective(goodsissue);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	GoodsissueExample ge = new GoodsissueExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	goodsissueMapper.deleteByExample(ge);
    	
    }
}
