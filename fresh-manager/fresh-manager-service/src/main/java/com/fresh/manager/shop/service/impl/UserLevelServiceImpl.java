package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.UserlevelMapper;
import com.fresh.manager.pojo.shop.Userlevel;
import com.fresh.manager.pojo.shop.UserlevelExample;
import com.fresh.manager.pojo.shop.UserlevelExample.Criteria;
import com.fresh.manager.shop.service.IUserLevelService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserLevelServiceImpl implements IUserLevelService {
    @Autowired
    private UserlevelMapper userlevelMapper;
    
    @Override
    public Userlevel queryById(Short id) {
        return userlevelMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Userlevel> queryList(Userlevel userlevel, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
   		UserlevelExample ge = new UserlevelExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(userlevel.getName())){
           	c.andNameLike("%"+userlevel.getName()+"%");
       	}
       	List<Userlevel> list = userlevelMapper.selectByExample(ge);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Userlevel userlevel) {
    	UserlevelExample ge = new UserlevelExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(userlevel.getName())){
           	c.andNameLike("%"+userlevel.getName()+"%");
       	}
		return userlevelMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Userlevel userlevel) {
    	userlevelMapper.insertSelective(userlevel);
    }

    @Override
    public void updateByPrimaryKeySelective(Userlevel userlevel) {
    	userlevelMapper.updateByPrimaryKeySelective(userlevel);
    }
    
    @Override
    public void deleteBatch(List<Short> values) {
    	UserlevelExample ge = new UserlevelExample();
   		Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	userlevelMapper.deleteByExample(ge);
    }
}
