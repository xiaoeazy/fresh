package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.GroupgoodsMapper;
import com.fresh.manager.pojo.shop.Groupgoods;
import com.fresh.manager.pojo.shop.GroupgoodsExample;
import com.fresh.manager.pojo.shop.GroupgoodsExample.Criteria;
import com.fresh.manager.shop.service.IGroupGoodsService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupGoodsServiceImpl implements IGroupGoodsService {
    @Autowired
    private GroupgoodsMapper groupgoodsMapper;

    @Override
    public Groupgoods queryById(Integer id) {
        return groupgoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Groupgoods> queryList(Groupgoods groupgoods, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
    	GroupgoodsExample ge = new GroupgoodsExample();
    	Criteria c =  ge.createCriteria();
    	List<Groupgoods> list = groupgoodsMapper.selectByExample(ge);
        return list;
    }
    
    @Override
	public List<Groupgoods> queryListWithGoodsInfo(Groupgoods obj){
    	return groupgoodsMapper.queryByIdWithGoodsInfo(obj);
    }

    @Override
    public long queryTotal(Groupgoods groupgoods) {
    	GroupgoodsExample ge = new GroupgoodsExample();
    	Criteria c =  ge.createCriteria();
    	if(groupgoods.getId()!=null){
    		c.andIdEqualTo(groupgoods.getId());
    	}
    	if(groupgoods.getGroupId()!=null){
    		c.andGroupIdEqualTo(groupgoods.getGroupId());
    	}
    	if(groupgoods.getGoodsId()!=null){
    		c.andGoodsIdEqualTo(groupgoods.getGoodsId());
    	}	
    	return groupgoodsMapper.countByExample(ge);
    }

    @Override
    public int insertSelective(Groupgoods groupGoods) {
        return groupgoodsMapper.insertSelective(groupGoods);
    }

    @Override
    public int updateByPrimaryKey(Groupgoods groupGoods) {
        return groupgoodsMapper.updateByPrimaryKey(groupGoods);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return groupgoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(List<Integer> values) {
    	GroupgoodsExample ge = new GroupgoodsExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
        return groupgoodsMapper.deleteByExample(ge);
    }
    
    @Override
    public int deleteBatchByGroupId(List<Integer> values) {
    	GroupgoodsExample ge = new GroupgoodsExample();
    	Criteria c =  ge.createCriteria();
    	c.andGroupIdIn(values);
        return groupgoodsMapper.deleteByExample(ge);
    }
    
    @Override
    public int deleteBatchByGoodsId(List<Integer> values) {
    	GroupgoodsExample ge = new GroupgoodsExample();
    	Criteria c =  ge.createCriteria();
    	c.andGoodsIdIn(values);
        return groupgoodsMapper.deleteByExample(ge);
    }
}
