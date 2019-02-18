package com.fresh.manager.mapper.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fresh.manager.pojo.shop.Group;
import com.fresh.manager.pojo.shop.GroupExample;

public interface GroupMapper {
    long countByExample(GroupExample example);

    int deleteByExample(GroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    List<Group> selectByExampleWithBLOBs(GroupExample example);

    List<Group> selectByExample(GroupExample example);

    Group selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByExampleWithBLOBs(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKeyWithBLOBs(Group record);

    int updateByPrimaryKey(Group record);
    
    
    
    List<Group> queryListWithGoods(Group example);
    Group queryWithGoodsByGroupId(Group example);

}