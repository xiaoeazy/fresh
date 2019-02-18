package com.fresh.manager.mapper.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fresh.manager.pojo.shop.Groupgoods;
import com.fresh.manager.pojo.shop.GroupgoodsExample;

public interface GroupgoodsMapper {
    long countByExample(GroupgoodsExample example);

    int deleteByExample(GroupgoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Groupgoods record);

    int insertSelective(Groupgoods record);

    List<Groupgoods> selectByExample(GroupgoodsExample example);

    Groupgoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Groupgoods record, @Param("example") GroupgoodsExample example);

    int updateByExample(@Param("record") Groupgoods record, @Param("example") GroupgoodsExample example);

    int updateByPrimaryKeySelective(Groupgoods record);

    int updateByPrimaryKey(Groupgoods record);
    
    
    List<Groupgoods> queryByIdWithGoodsInfo(Groupgoods record);
}