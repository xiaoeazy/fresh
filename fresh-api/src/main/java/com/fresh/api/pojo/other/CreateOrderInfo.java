package com.fresh.api.pojo.other;

import com.fresh.manager.pojo.shop.Order;
import com.fresh.manager.pojo.shop.Ordergoods;

public class CreateOrderInfo {
	private Order order;
	private Ordergoods ordergoods;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Ordergoods getOrdergoods() {
		return ordergoods;
	}
	public void setOrdergoods(Ordergoods ordergoods) {
		this.ordergoods = ordergoods;
	}
	
	
}
