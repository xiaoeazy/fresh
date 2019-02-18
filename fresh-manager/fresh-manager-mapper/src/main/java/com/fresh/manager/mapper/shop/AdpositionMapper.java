package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Adposition;
import com.fresh.manager.pojo.shop.AdpositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdpositionMapper {
    long countByExample(AdpositionExample example);

    int deleteByExample(AdpositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Adposition record);

    int insertSelective(Adposition record);

    List<Adposition> selectByExample(AdpositionExample example);

    Adposition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Adposition record, @Param("example") AdpositionExample example);

    int updateByExample(@Param("record") Adposition record, @Param("example") AdpositionExample example);

    int updateByPrimaryKeySelective(Adposition record);

    int updateByPrimaryKey(Adposition record);
}