package com.fresh.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.SysMacroMapper;
import com.fresh.manager.pojo.SysMacro;
import com.fresh.manager.pojo.SysMacroExample;
import com.fresh.manager.pojo.SysMacroExample.Criteria;
import com.fresh.manager.service.ISysMacroService;
//import com.platform.cache.J2CacheUtils;
import com.github.pagehelper.PageHelper;

/**
 * 通用字典表Service实现类
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMacroServiceImpl implements ISysMacroService {
    @Autowired
    private SysMacroMapper sysMacroMapper;
    @Override
    public SysMacro queryById(Long macroId) {
        return sysMacroMapper.selectByPrimaryKey(macroId);
    }

    @Override
   	public List<SysMacro> queryList(SysMacro sysMacro, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
   		SysMacroExample ge =  new SysMacroExample();
   		Criteria c = ge.createCriteria();
       	if(sysMacro.getId()!=null){
           	c.andIdEqualTo(sysMacro.getId());
       	}
       	if(!StringUtils.isEmpty(sysMacro.getName())){
           	c.andNameEqualTo(sysMacro.getName());
       	}
       	if(!StringUtils.isEmpty(sysMacro.getRemark())){
           	c.andRemarkEqualTo(sysMacro.getRemark());
       	}
       	if(!StringUtils.isEmpty(sysMacro.getValue())){
           	c.andValueEqualTo(sysMacro.getValue());
       	}
       	if(sysMacro.getOrderNum()!=null){
           	c.andOrderNumEqualTo(sysMacro.getOrderNum());
       	}
       	if(sysMacro.getParentId()!=null){
           	c.andParentIdEqualTo(sysMacro.getParentId());
       	}
       	if(sysMacro.getStatus()!=null){
           	c.andStatusEqualTo(sysMacro.getStatus());
       	}
       	if(sysMacro.getType()!=null){
           	c.andTypeEqualTo(sysMacro.getType());
       	}
       	
       	List<SysMacro> list = sysMacroMapper.selectByExample(ge);
           
        return list;
   	}

    @Override
    public long queryTotal(SysMacro sysMacro) {
    	SysMacroExample ge =  new SysMacroExample();
   		Criteria c = ge.createCriteria();
       	if(sysMacro.getId()!=null){
           	c.andIdEqualTo(sysMacro.getId());
       	}
       	if(!StringUtils.isEmpty(sysMacro.getName())){
           	c.andNameEqualTo(sysMacro.getName());
       	}
       	if(!StringUtils.isEmpty(sysMacro.getRemark())){
           	c.andRemarkEqualTo(sysMacro.getRemark());
       	}
       	if(!StringUtils.isEmpty(sysMacro.getValue())){
           	c.andValueEqualTo(sysMacro.getValue());
       	}
       	if(sysMacro.getOrderNum()!=null){
           	c.andOrderNumEqualTo(sysMacro.getOrderNum());
       	}
       	if(sysMacro.getParentId()!=null){
           	c.andParentIdEqualTo(sysMacro.getParentId());
       	}
       	if(sysMacro.getStatus()!=null){
           	c.andStatusEqualTo(sysMacro.getStatus());
       	}
       	if(sysMacro.getType()!=null){
           	c.andTypeEqualTo(sysMacro.getType());
       	}
        return sysMacroMapper.countByExample(ge);
    }

    @Override
    public int insertSelective(SysMacro sysMacro) {
        sysMacro.setGmtCreate(new Date());
        return sysMacroMapper.insertSelective(sysMacro);
    }

    @Override
    public int update(SysMacro sysMacro) {
        sysMacro.setGmtModified(new Date());
       return  sysMacroMapper.updateByPrimaryKeySelective(sysMacro);
    }

    @Override
    public int deleteByPrimaryKey(Long macroId) {
        return sysMacroMapper.deleteByPrimaryKey(macroId);
    }

    
    @Override
	public int deleteBatch(List<Long> ids) {
    	SysMacroExample se = new SysMacroExample();
		Criteria criteria =  se.createCriteria();
		criteria.andIdIn(ids);
		return sysMacroMapper.deleteByExample(se);
	}

    @Override
    public List<SysMacro> queryMacrosByValue(String value) {
        return sysMacroMapper.queryMacrosByValue(value);
    }
}
