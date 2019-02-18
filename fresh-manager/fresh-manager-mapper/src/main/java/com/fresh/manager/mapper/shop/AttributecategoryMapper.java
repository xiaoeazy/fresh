package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Attributecategory;
import com.fresh.manager.pojo.shop.AttributecategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttributecategoryMapper {
    long countByExample(AttributecategoryExample example);

    int deleteByExample(AttributecategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attributecategory record);

    int insertSelective(Attributecategory record);

    List<Attributecategory> selectByExample(AttributecategoryExample example);

    Attributecategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attributecategory record, @Param("example") AttributecategoryExample example);

    int updateByExample(@Param("record") Attributecategory record, @Param("example") AttributecategoryExample example);

    int updateByPrimaryKeySelective(Attributecategory record);

    int updateByPrimaryKey(Attributecategory record);
}