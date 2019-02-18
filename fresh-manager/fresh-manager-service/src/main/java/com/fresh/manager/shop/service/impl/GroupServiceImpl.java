package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.GroupMapper;
import com.fresh.manager.mapper.shop.GroupgoodsMapper;
import com.fresh.manager.pojo.shop.Goods;
import com.fresh.manager.pojo.shop.Group;
import com.fresh.manager.pojo.shop.GroupExample;
import com.fresh.manager.pojo.shop.GroupExample.Criteria;
import com.fresh.manager.pojo.shop.Groupgoods;
import com.fresh.manager.pojo.shop.GroupgoodsExample;
import com.fresh.manager.shop.service.IGroupService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupServiceImpl implements IGroupService {
    public static final String GROUP_SN_PREFIX="TG_";
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupgoodsMapper groupgoodsMapper;
    
    @Override
    public List<Group> queryListWithGoods(Group group, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
        return groupMapper.queryListWithGoods(group);
    }
    @Override
    public  Group queryListWithGoodsByGroupId(Group group){
    	return groupMapper.queryWithGoodsByGroupId(group);
    }
   

  

    @Override
    public List<Group> queryList(Group group, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
    	
    	GroupExample ge = new GroupExample();
    	Criteria c =  ge.createCriteria();
    	if(!StringUtils.isEmpty(group.getGroupName()))
    		c.andGroupNameEqualTo(group.getGroupName());
       	List<Group> list = groupMapper.selectByExample(ge);
           
        return list;
   	}
    
    @Override
    public long queryTotal(Group group){
    	GroupExample ge = new GroupExample();
    	Criteria c =  ge.createCriteria();
    	if(!StringUtils.isEmpty(group.getGroupName()))
    		c.andGroupNameEqualTo(group.getGroupName());
    	return groupMapper.countByExample(ge);
    }

    @Override
    public Group queryById(Integer id) {
        return groupMapper.selectByPrimaryKey(id);
    }
  
    @Override
    public int save(Group group) {
        int i =  groupMapper.insertSelective(group);
        String groupSn = GROUP_SN_PREFIX + group.getId();
        group.setGroupSn(groupSn);
        groupMapper.updateByPrimaryKeySelective(group);
        
        if(group.getGoodsList()!=null){ 
        	for(Goods gge :group.getGoodsList()){
        		Groupgoods gg = new Groupgoods();
        		gg.setGroupId(group.getId());
        		gg.setGoods(gge);
        		groupgoodsMapper.insertSelective(gg);
        	}
        }
        
        
//        if(1>0)
//        	throw new RuntimeException("eee");
        return i;
    }

    @Override
    public int update(Group group) {
        int i = groupMapper.updateByPrimaryKey(group);
        if(group.getGoodsList()!=null){
        	
        	GroupgoodsExample ggdelete = new GroupgoodsExample();
        	com.fresh.manager.pojo.shop.GroupgoodsExample.Criteria c =ggdelete.createCriteria();
        	c.andGroupIdEqualTo(group.getId());
        	groupgoodsMapper.deleteByExample(ggdelete);
        	
        	for(Goods gge :group.getGoodsList()){
        		Groupgoods gg = new Groupgoods();
        		gg.setGroupId(group.getId());
        		gg.setGoods(gge);
        		groupgoodsMapper.insertSelective(gg);
        	}
        }
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
    	Group g = new Group();
    	g.setIsDelete(true);
    	g.setId(id);
        return groupMapper.updateByPrimaryKey(g);
    }

    @Override
    public int deleteBatch(List<Integer> values) {
    	
    	GroupExample ge = new GroupExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	c.andIsDeleteEqualTo(true);
    	
    	Group g = new Group();
    	g.setIsDelete(true);
    	return groupMapper.updateByExample(g, ge);
//    	groupGoodsMapper.deleteBatchByGroupId(ids);
//        return groupMapper.deleteBatch(ids);
    }
}
