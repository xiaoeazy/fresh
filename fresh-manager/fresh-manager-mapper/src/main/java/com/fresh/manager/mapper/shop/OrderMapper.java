package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Order;
import com.fresh.manager.pojo.shop.OrderExample;
import com.fresh.manager.pojo.shop.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    
    List<Order> queryList(Order record);
    Order queryById(int id );
    List<Order> selectWithGoods(Order order);
}