package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Goodsattribute;
import com.fresh.manager.pojo.shop.GoodsattributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsattributeMapper {
    long countByExample(GoodsattributeExample example);

    int deleteByExample(GoodsattributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodsattribute record);

    int insertSelective(Goodsattribute record);

    List<Goodsattribute> selectByExampleWithBLOBs(GoodsattributeExample example);

    List<Goodsattribute> selectByExample(GoodsattributeExample example);

    Goodsattribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodsattribute record, @Param("example") GoodsattributeExample example);

    int updateByExampleWithBLOBs(@Param("record") Goodsattribute record, @Param("example") GoodsattributeExample example);

    int updateByExample(@Param("record") Goodsattribute record, @Param("example") GoodsattributeExample example);

    int updateByPrimaryKeySelective(Goodsattribute record);

    int updateByPrimaryKeyWithBLOBs(Goodsattribute record);

    int updateByPrimaryKey(Goodsattribute record);
}