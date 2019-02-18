package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Productskuspecification;
import com.fresh.manager.pojo.shop.ProductskuspecificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductskuspecificationMapper {
    long countByExample(ProductskuspecificationExample example);

    int deleteByExample(ProductskuspecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Productskuspecification record);

    int insertSelective(Productskuspecification record);

    List<Productskuspecification> selectByExample(ProductskuspecificationExample example);

    Productskuspecification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Productskuspecification record, @Param("example") ProductskuspecificationExample example);

    int updateByExample(@Param("record") Productskuspecification record, @Param("example") ProductskuspecificationExample example);

    int updateByPrimaryKeySelective(Productskuspecification record);

    int updateByPrimaryKey(Productskuspecification record);
}