package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Ordergoods;
import com.fresh.manager.pojo.shop.OrdergoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdergoodsMapper {
    long countByExample(OrdergoodsExample example);

    int deleteByExample(OrdergoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ordergoods record);

    int insertSelective(Ordergoods record);

    List<Ordergoods> selectByExampleWithBLOBs(OrdergoodsExample example);

    List<Ordergoods> selectByExample(OrdergoodsExample example);

    Ordergoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ordergoods record, @Param("example") OrdergoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Ordergoods record, @Param("example") OrdergoodsExample example);

    int updateByExample(@Param("record") Ordergoods record, @Param("example") OrdergoodsExample example);

    int updateByPrimaryKeySelective(Ordergoods record);

    int updateByPrimaryKeyWithBLOBs(Ordergoods record);

    int updateByPrimaryKey(Ordergoods record);
}