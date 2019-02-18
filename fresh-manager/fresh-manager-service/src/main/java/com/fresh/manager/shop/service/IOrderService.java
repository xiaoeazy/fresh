package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Order;

public interface IOrderService {
	/**
	 * 插入
	 * @param order
	 */
	void insertSelective(Order order);
	/**
	 * 更新
	 * @param order
	 */
	void updateByPrimaryKeySelective(Order order);
	/**
	 * 查询总数
	 * @param order
	 * @return
	 */
	long queryTotal(Order order);
	/**
	 * 查询列表
	 * @param order
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Order> queryList(Order order, Integer pageNum, Integer pageSize);
	/**
	 * 根据主键查询
	 * @param orderId
	 * @return
	 */
	Order queryById(Integer orderId);
/**
 * 批量删除
 * @param values
 */
	void deleteBatch(List<Integer> values);
/**
 * 确定收货
 * @param id
 * @return
 */
	int confirm(Integer id);
/**
 * 发货
 * @param order
 * @return
 */
	int sendGoods(Order order);
	
	
	
	
	
	/**
	 * 根据sn查询
	 * @param out_trade_no
	 * @return
	 */
	Order queryBySn(String out_trade_no);
	/**
	 * 查询包含商品
	 * @param order
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Order> queryListWithGoods(Order order, int pageNum, int pageSize);
	/**
	 * 根据orderid查询带有goods的order
	 * @param orderId
	 * @return
	 */
	Order queryWithGoods(Integer orderId);

   
}
