package com.fresh.manager.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.fresh.common.exception.RRException;
import com.fresh.manager.mapper.SysConfigMapper;
import com.fresh.manager.pojo.SysConfig;
import com.fresh.manager.pojo.SysConfigExample;
import com.fresh.manager.pojo.SysConfigExample.Criteria;
import com.fresh.manager.service.ISysConfigService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysConfigServiceImpl implements ISysConfigService {
	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	@Override
	public int insertSelective(SysConfig config) {
		return sysConfigMapper.insertSelective(config);
	}

	@Override
	public int updateByPrimaryKeySelective(SysConfig config) {
		return sysConfigMapper.updateByPrimaryKeySelective(config);
	}

	@Override
	public int updateValueByKey(String name, String value) {
		SysConfig c = new SysConfig();
		c.setValue(value);
		SysConfigExample se = new SysConfigExample();
		Criteria criteria =  se.createCriteria();
		criteria.andNameEqualTo(name);
		return sysConfigMapper.updateByExampleSelective(c, se);
	}

	@Override
	public int deleteBatch(List<Long> ids) {
		SysConfigExample se = new SysConfigExample();
		Criteria criteria =  se.createCriteria();
		criteria.andIdIn(ids);
		return sysConfigMapper.deleteByExample(se);
	}

	@Override
	public List<SysConfig> queryList(SysConfig sysConfig, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		SysConfigExample ge = new SysConfigExample();
		Criteria c =ge.createCriteria();
    	if(sysConfig.getId()!=null){
        	c.andIdEqualTo(sysConfig.getId());
    	}
    	if(!StringUtils.isEmpty(sysConfig.getName())){
        	c.andNameEqualTo(sysConfig.getName());
    	}
    	if(!StringUtils.isEmpty(sysConfig.getRemark())){
        	c.andRemarkEqualTo(sysConfig.getRemark());
    	}
    	if(sysConfig.getStatus()!=null){
        	c.andStatusEqualTo(sysConfig.getStatus());
    	}
    	if(!StringUtils.isEmpty(sysConfig.getValue())){
        	c.andValueEqualTo(sysConfig.getValue());
    	}
    	c.andStatusEqualTo((short) 1);
        return sysConfigMapper.selectByExample(ge);
	}

	@Override
	public long queryTotal(SysConfig sysConfig) {
		SysConfigExample ge = new SysConfigExample();
		Criteria c =ge.createCriteria();
    	if(sysConfig.getId()!=null){
        	c.andIdEqualTo(sysConfig.getId());
    	}
    	if(!StringUtils.isEmpty(sysConfig.getName())){
        	c.andNameEqualTo(sysConfig.getName());
    	}
    	if(!StringUtils.isEmpty(sysConfig.getRemark())){
        	c.andRemarkEqualTo(sysConfig.getRemark());
    	}
    	if(sysConfig.getStatus()!=null){
        	c.andStatusEqualTo(sysConfig.getStatus());
    	}
    	if(!StringUtils.isEmpty(sysConfig.getValue())){
        	c.andValueEqualTo(sysConfig.getValue());
    	}
    	c.andStatusEqualTo((short) 1);
		return sysConfigMapper.countByExample(ge);
	}

	@Override
	public SysConfig queryById(Long id) {
		return sysConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String value = sysConfigMapper.selectByName(key);
		if(StringUtils.isBlank(value)){
			return defaultValue;
		}
		return value;
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RRException("获取参数失败");
		}
	}
}
