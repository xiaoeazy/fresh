package com.fresh.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.components.RegionCacheComponent;
import com.fresh.manager.mapper.SysRegionMapper;
import com.fresh.manager.pojo.SysRegion;
import com.fresh.manager.pojo.SysRegionExample;
import com.fresh.manager.pojo.SysRegionExample.Criteria;
import com.fresh.manager.service.ISysRegionService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class SysRegionServiceImpl implements ISysRegionService {
    @Autowired
    private SysRegionMapper sysRegionMapper;
    @Autowired
    private RegionCacheComponent regionCacheComponent;

    @Override
    public SysRegion queryById(Integer id) {
        return sysRegionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRegion> queryList(SysRegion region,int pageNum ,int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
        return sysRegionMapper.queryList(region);
    }

    @Override
    public int queryTotal(SysRegion region) {
        return sysRegionMapper.queryTotal(region);
    }

    @Override
    public int insertSelective(SysRegion region) {
        int i = sysRegionMapper.insertSelective(region);
        regionCacheComponent.reload();
        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(SysRegion region) {
    	
        int i =sysRegionMapper.updateByPrimaryKeySelective(region);
        regionCacheComponent.reload();
        return  i ;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = sysRegionMapper.deleteByPrimaryKey(id);
        regionCacheComponent.reload();
        return i;
    }

    @Override
    public int deleteBatch(List<Integer> ids) {
    	SysRegionExample se = new SysRegionExample();
		Criteria criteria =  se.createCriteria();
		criteria.andIdIn(ids);
		int i =  sysRegionMapper.deleteByExample(se);
		regionCacheComponent.reload();
		return i;
    }
}
