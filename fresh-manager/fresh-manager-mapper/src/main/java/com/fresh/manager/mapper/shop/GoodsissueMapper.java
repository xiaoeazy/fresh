package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Goodsissue;
import com.fresh.manager.pojo.shop.GoodsissueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsissueMapper {
    long countByExample(GoodsissueExample example);

    int deleteByExample(GoodsissueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodsissue record);

    int insertSelective(Goodsissue record);

    List<Goodsissue> selectByExample(GoodsissueExample example);

    Goodsissue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodsissue record, @Param("example") GoodsissueExample example);

    int updateByExample(@Param("record") Goodsissue record, @Param("example") GoodsissueExample example);

    int updateByPrimaryKeySelective(Goodsissue record);

    int updateByPrimaryKey(Goodsissue record);
}