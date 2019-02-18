package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.FeedbackMapper;
import com.fresh.manager.pojo.shop.Feedback;
import com.fresh.manager.pojo.shop.FeedbackExample;
import com.fresh.manager.pojo.shop.FeedbackExample.Criteria;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IFeedbackService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;


    @Override
    public Feedback queryById(Integer FeedbackId) {
        return feedbackMapper.selectByPrimaryKey(FeedbackId);
    }
    @Override
   	public List<Feedback> queryList(Feedback feedback, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
   		FeedbackExample ge = new FeedbackExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(feedback.getUserName())){
           	c.andUserNameLike("%"+feedback.getUserName()+"%");
       	}
       	List<Feedback> list = feedbackMapper.selectByExample(ge);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Feedback feedback) {
    	FeedbackExample ge = new FeedbackExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(feedback.getUserName())){
           	c.andUserNameLike("%"+feedback.getUserName()+"%");
       	}
		return feedbackMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Feedback feedback) {
    	feedbackMapper.insertSelective(feedback);
    }

    @Override
    public void updateByPrimaryKeySelective(Feedback feedback) {
    	feedbackMapper.updateByPrimaryKeySelective(feedback);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	FeedbackExample ge = new FeedbackExample();
    	Criteria c =  ge.createCriteria();
    	c.andMsgIdIn(values);
    	feedbackMapper.deleteByExample(ge);
    	
    }
    
    
    @Override
	public void saveForApi(User loginUser, Feedback feedback){
		feedback.setUserId(loginUser.getId());
		feedback.setUserName(loginUser.getUsername());
		feedback.setStatus((short) 1);
		feedbackMapper.insertSelective(feedback);
	}
}
