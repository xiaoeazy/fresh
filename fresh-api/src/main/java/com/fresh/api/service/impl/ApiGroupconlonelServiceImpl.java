package com.fresh.api.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.api.mapper.GroupcolonelMapper;
import com.fresh.api.pojo.Groupcolonel;
import com.fresh.api.pojo.GroupcolonelExample;
import com.fresh.api.pojo.GroupcolonelExample.Criteria;
import com.fresh.api.service.IApiGroupconlonelService;
import com.fresh.common.cache.service.JedisClient;
import com.fresh.manager.pojo.shop.User;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApiGroupconlonelServiceImpl implements IApiGroupconlonelService {
	
	@Autowired
	private GroupcolonelMapper groupcolonelMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${COLONEL_GROUP_SN}")
	private String COLONEL_GROUP_SN;
	
	@Value("${COLONEL_GROUP_KEY}")
	private String COLONEL_GROUP_KEY;
	
	@Value("${COLONEL_GROUP_ID}")
	private String COLONEL_GROUP_ID;
	
	@Override
    public List<Groupcolonel> queryList(Groupcolonel groupcolonel, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	GroupcolonelExample ge = new GroupcolonelExample();
    	if(groupcolonel.getCreateUserId()!=null){
    		Criteria c =ge.createCriteria();
        	c.andCreateUserIdEqualTo(groupcolonel.getCreateUserId());
    	}
    	if(groupcolonel.getGroupEndTime()!=null){
    		Criteria c =ge.createCriteria();
        	c.andGroupEndTimeLessThan(new Date());
    	}
        return groupcolonelMapper.selectByExample(ge);
    }
	
	@Override
	public void save(User loginUser,Groupcolonel g){
		String string = jedisClient.get(COLONEL_GROUP_KEY);
		if (StringUtils.isBlank(string)) {
			jedisClient.set(COLONEL_GROUP_KEY, COLONEL_GROUP_ID);
		}
		long incr = jedisClient.incr(COLONEL_GROUP_KEY);
		
		g.setColonelGroupSn(COLONEL_GROUP_SN+incr);
    	g.setCreateUserId(loginUser.getId());
		groupcolonelMapper.insertSelective(g);
	}
	
	@Override
	public int queryTotal(Groupcolonel groupcolonel){
		GroupcolonelExample ge = new GroupcolonelExample();
		if(groupcolonel.getCreateUserId()!=null){
    		Criteria c =ge.createCriteria();
        	c.andCreateUserIdEqualTo(groupcolonel.getCreateUserId());
    	}
    	if(groupcolonel.getGroupEndTime()!=null){
    		Criteria c =ge.createCriteria();
        	c.andGroupEndTimeLessThan(new Date());
    	}
		return (int) groupcolonelMapper.countByExample(ge);
	}
	
	@Override
	public Groupcolonel queryById(int colonelGroupId){
		return groupcolonelMapper.selectByPrimaryKey(colonelGroupId);
	}
	
}
