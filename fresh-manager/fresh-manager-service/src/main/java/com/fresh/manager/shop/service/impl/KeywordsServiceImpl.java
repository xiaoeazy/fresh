package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.KeywordsMapper;
import com.fresh.manager.pojo.shop.Keywords;
import com.fresh.manager.pojo.shop.KeywordsExample;
import com.fresh.manager.pojo.shop.KeywordsExample.Criteria;
import com.fresh.manager.shop.service.IKeywordsService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class KeywordsServiceImpl implements IKeywordsService {
    @Autowired
    private KeywordsMapper keywordsMapper;


    @Override
    public Keywords queryById(Integer id) {
        return keywordsMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Keywords> queryList(Keywords keywords, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
   		KeywordsExample ge = new KeywordsExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(keywords.getKeyword())){
           	c.andKeywordLike("%"+keywords.getKeyword()+"%");
       	}
       	List<Keywords> list = keywordsMapper.selectByExample(ge);
        return list;
   	}
    
    @Override
    public long queryTotal(Keywords keywords) {
    	KeywordsExample ge = new KeywordsExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(keywords.getKeyword())){
           	c.andKeywordLike("%"+keywords.getKeyword()+"%");
       	}
		return keywordsMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Keywords keywords) {
    	keywordsMapper.insertSelective(keywords);
    }

    @Override
    public void updateByPrimaryKeySelective(Keywords keywords) {
    	keywordsMapper.updateByPrimaryKeySelective(keywords);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	KeywordsExample ge = new KeywordsExample();
   		Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	keywordsMapper.deleteByExample(ge);
    }
}
