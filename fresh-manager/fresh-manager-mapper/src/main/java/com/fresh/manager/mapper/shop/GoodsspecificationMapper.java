package com.fresh.manager.mapper.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fresh.manager.pojo.shop.Goodsspecification;
import com.fresh.manager.pojo.shop.GoodsspecificationExample;

public interface GoodsspecificationMapper {
    long countByExample(GoodsspecificationExample example);

    int deleteByExample(GoodsspecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodsspecification record);

    int insertSelective(Goodsspecification record);

    List<Goodsspecification> selectByExample(GoodsspecificationExample example);

    Goodsspecification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodsspecification record, @Param("example") GoodsspecificationExample example);

    int updateByExample(@Param("record") Goodsspecification record, @Param("example") GoodsspecificationExample example);

    int updateByPrimaryKeySelective(Goodsspecification record);

    int updateByPrimaryKey(Goodsspecification record);
    
    
    
    List<Goodsspecification> queryList(Goodsspecification goodsspecification);
    
    int queryTotal(Goodsspecification goodsspecification);
}