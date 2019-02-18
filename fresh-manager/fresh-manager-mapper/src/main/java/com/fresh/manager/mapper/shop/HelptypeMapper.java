package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Helptype;
import com.fresh.manager.pojo.shop.HelptypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelptypeMapper {
    long countByExample(HelptypeExample example);

    int deleteByExample(HelptypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Helptype record);

    int insertSelective(Helptype record);

    List<Helptype> selectByExample(HelptypeExample example);

    Helptype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Helptype record, @Param("example") HelptypeExample example);

    int updateByExample(@Param("record") Helptype record, @Param("example") HelptypeExample example);

    int updateByPrimaryKeySelective(Helptype record);

    int updateByPrimaryKey(Helptype record);
}