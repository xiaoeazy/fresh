package com.fresh.manager.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.SysOssMapper;
import com.fresh.manager.pojo.SysOss;
import com.fresh.manager.pojo.SysOssExample;
import com.fresh.manager.pojo.SysOssExample.Criteria;
import com.fresh.manager.service.ISysOssService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOssServiceImpl implements ISysOssService {
    @Autowired
    private SysOssMapper sysOssMapper;

    @Override
    public SysOss queryById(Long id) {
        return sysOssMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysOss> queryList(SysOss sysOss, Integer pageNum, Integer pageSize) {
    	if(pageNum != null && pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
    	SysOssExample ge = new SysOssExample();
		 Criteria c = ge.createCriteria();
    	if(sysOss.getId()!=null){
        	c.andIdEqualTo(sysOss.getId());
    	}
    	if(!StringUtils.isEmpty(sysOss.getUrl())){
        	c.andUrlEqualTo(sysOss.getUrl());
    	}
    	
        return sysOssMapper.selectByExample(ge);
    }

    @Override
    public long queryTotal(SysOss sysOss) {
    	SysOssExample ge = new SysOssExample();
		 Criteria c = ge.createCriteria();
	   	if(sysOss.getId()!=null){
	       	c.andIdEqualTo(sysOss.getId());
	   	}
	   	if(!StringUtils.isEmpty(sysOss.getUrl())){
	       	c.andUrlEqualTo(sysOss.getUrl());
	   	}
   	
       return sysOssMapper.countByExample(ge);
    }

    @Override
    public int insertSelective(SysOss sysOss) {
        return sysOssMapper.insertSelective(sysOss);
    }

    @Override
    public int updateByPrimaryKeySelective(SysOss sysOss) {
        return sysOssMapper.updateByPrimaryKeySelective(sysOss);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
       return  sysOssMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
    	SysOssExample se = new SysOssExample();
		Criteria criteria =  se.createCriteria();
		criteria.andIdIn(ids);
		return sysOssMapper.deleteByExample(se);
    }

}
