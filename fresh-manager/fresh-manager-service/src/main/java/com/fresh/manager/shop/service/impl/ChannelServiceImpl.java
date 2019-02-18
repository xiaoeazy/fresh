package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.ChannelMapper;
import com.fresh.manager.pojo.shop.Channel;
import com.fresh.manager.pojo.shop.ChannelExample;
import com.fresh.manager.pojo.shop.ChannelExample.Criteria;
import com.fresh.manager.shop.service.IChannelService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class ChannelServiceImpl implements IChannelService {
    @Autowired
    private ChannelMapper channelMapper;
    @Override
    public Channel queryById(Integer id) {
        return channelMapper.selectByPrimaryKey(id);
    }
    
    @Override
   	public List<Channel> queryList(Channel channel, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
   		ChannelExample ge = new ChannelExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(channel.getName())){
           	c.andNameLike("%"+channel.getName()+"%");
       	}
        return channelMapper.selectByExample(ge);
   	}
    

    @Override
    public long queryTotal(Channel channel) {
    	ChannelExample ge = new ChannelExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(channel.getName())){
           	c.andNameLike("%"+channel.getName()+"%");
       	}
		return channelMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Channel channel) {
    	channelMapper.insertSelective(channel);
    }

    @Override
    public void updateByPrimaryKeySelective(Channel channel) {
    	channelMapper.updateByPrimaryKeySelective(channel);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	ChannelExample ge = new ChannelExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	channelMapper.deleteByExample(ge);
    }
}
