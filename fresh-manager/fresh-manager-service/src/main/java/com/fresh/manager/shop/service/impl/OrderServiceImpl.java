package com.fresh.manager.shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.cache.service.JedisClient;
import com.fresh.common.exception.RRException;
import com.fresh.manager.mapper.shop.OrderMapper;
import com.fresh.manager.mapper.shop.ShippingMapper;
import com.fresh.manager.pojo.shop.Goods;
import com.fresh.manager.pojo.shop.Order;
import com.fresh.manager.pojo.shop.OrderExample;
import com.fresh.manager.pojo.shop.OrderExample.Criteria;
import com.fresh.manager.pojo.shop.Shipping;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IOrderService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ShippingMapper shippingMapper;
	
	

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_KEY}")
	private String ORDER_KEY;
	
	@Value("${ORDER_SN}")
	private String ORDER_SN;
	
	@Value("${ORDER_ID}")
	private String ORDER_ID;
	

    @Override
    public Order queryById(Integer orderId) {
        return orderMapper.queryById(orderId);
    }
    @Override
   	public List<Order> queryList(Order order, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
       	List<Order> list = orderMapper.queryList(order);
        return list;
   	}
    
    @Override
    public long queryTotal(Order order) {
        OrderExample ge = new OrderExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(order.getOrderSn())){
           	c.andOrderSnLike("%"+order.getOrderSn()+"%");
       	}
    	if(order.getShippingStatus()!=null){
           	c.andShippingStatusEqualTo(order.getShippingStatus());
       	}
    	if(order.getPayStatus()!=null){
           	c.andPayStatusEqualTo(order.getPayStatus());
       	}
    	if(order.getOrderStatus()!=null){
           	c.andOrderStatusEqualTo(order.getOrderStatus());
       	}
    	if(order.getOrderType()!=null){
           	c.andOrderTypeEqualTo(order.getOrderType());
       	}
    	if(order.getUserId()!=null){
        	c.andUserIdEqualTo( order.getUserId());
    	}
		return orderMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Order order) {
    	orderMapper.insertSelective(order);
    }

    @Override
    public void updateByPrimaryKeySelective(Order order) {
    	orderMapper.updateByPrimaryKeySelective(order);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	OrderExample ge = new OrderExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	orderMapper.deleteByExample(ge);
    	
    }

    
    @Override
    public int confirm(Integer id) {
        Order order = orderMapper.queryById(id);
        Short shippingStatus = order.getShippingStatus();//发货状态
        Short payStatus = order.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款，不能确认收货！");
        }
        if (4 == shippingStatus) {
            throw new RRException("此订单处于退货状态，不能确认收货！");
        }
        if (0 == shippingStatus) {
            throw new RRException("此订单未发货，不能确认收货！");
        }
        order.setShippingStatus((short) 2);
        order.setOrderStatus((short) 301);
        return orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public int sendGoods(Order order) {
        Short payStatus = order.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款！");
        }
        Shipping shipping = shippingMapper.selectByPrimaryKey((int)order.getShippingId());
        if (null != shipping) {
            order.setShippingName(shipping.getName());
        }
        order.setOrderStatus((short) 300);//订单已发货
        order.setShippingStatus((short) 1);//已发货
        return orderMapper.updateByPrimaryKey(order);
    }
    
    

//	@Override
//	public Map<String, Object> createOrder(User loginUser, Integer colonelGroupId, List<Goods> goodsList, Integer addressId)
//		
//		 Map<String, Object> resultObj = new HashMap<String, Object>();
//		// TODO Auto-generated method stub
//		// 商品总价
//		BigDecimal goodsTotalPrice = new BigDecimal(0.00);
//					
//		Groupcolonel groupcolonel  = groupconlonelService.queryById(colonelGroupId);
//		if(groupcolonel==null){
//			throw new RRException("该团购已经不存在了！");
//		}
//		
//		Date endTime = groupcolonel.getGroupEndTime();
//		int i = endTime.compareTo(new Date());
//		if(i<0){
//			throw new RRException("该团购已经结束");
//		}
//		
//		for (Goods gg : goodsList) {
//			Groupgoods selectgg = new Groupgoods();
//			selectgg.setGroupId(groupcolonel.getGroupId());
//			selectgg.setGoodsId(gg.getId());
//			Groupgoods groupgoods = groupgoodsService.queryByIdWithGoodsInfo(selectgg);
//			if (groupgoods == null)
//				throw new RRException("该物品已经不再该团购列表中了！");
//			goodsTotalPrice =goodsTotalPrice.add(groupgoods.getGoods().getRetailPrice().multiply(new BigDecimal(gg.getBuyNum()))) ;
//		}
//		
//		// 订单总价
//		Integer  freightPrice = 0 ; // 快递费用
//		BigDecimal orderTotalPrice = goodsTotalPrice.add( new BigDecimal(freightPrice));
//
//		BigDecimal couponPrice = new BigDecimal(0.00); // 优惠券金额
//		BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice); // 减去其它支付的金额后，要实际支付的金额
//		
//		
//		//===order =====
//		 Order orderInfo = new Order();
//		 orderInfo.setId(null);
//		 
//		 String string = jedisClient.get(ORDER_KEY);
//		 if (StringUtils.isBlank(string)) {
//			jedisClient.set(ORDER_KEY, ORDER_ID);
//		 }
//		 long incr = jedisClient.incr(ORDER_KEY);
//		 
//		 orderInfo.setOrderSn(ORDER_SN+incr);
//		 orderInfo.setUserId(loginUser.getId());
//		 orderInfo.setOrderStatus(0);
//		 orderInfo.setShippingStatus((short) 0);
//		 orderInfo.setPayStatus((short) 0);
//		 orderInfo.setActualPrice(actualPrice);
//		 orderInfo.setOrderPrice(orderTotalPrice);
//		 orderInfo.setGoodsPrice(goodsTotalPrice);
//		 orderInfo.setAddTime(new Date());
//		 orderInfo.setConfirmTime(null);
//		 orderInfo.setPayTime(null);
//		 orderInfo.setPayId(null);
//		 orderInfo.setPayName(null);
//		 orderInfo.setCallbackStatus(null);
//		 orderInfo.setFullCutPrice(null);
//		 orderInfo.setOrderType((short) 5);
//		 
//		 
//		 //地址
//		 if(addressId!=null){
//			 Address useraddress = addressService.queryById(addressId);
//			 orderInfo.setConsignee(useraddress.getUserName());
//			 orderInfo.setCountry(useraddress.getNationalCode());
//			 orderInfo.setProvince(useraddress.getProvinceName());
//			 orderInfo.setCity(useraddress.getCityName());
//			 orderInfo.setDistrict(useraddress.getCountyName());
//			 orderInfo.setAddress(useraddress.getDetailInfo());
//			 orderInfo.setMobile(useraddress.getTelNumber());
//			
//		 }
//		 
//		 //补充说明
//		 orderInfo.setPostscript(null);
//		 
//		 //快递
//		 orderInfo.setShippingId(null);
//		 orderInfo.setShippingName(null);
//		 orderInfo.setShippingFee(null);
//		 orderInfo.setShippingNo(null);
//		 
//		
//		
//		 
//		 //积分
//		 orderInfo.setIntegral(null);
//		 orderInfo.setIntegralMoney(null);
//		 //配送费用
//		 orderInfo.setFreightPrice(freightPrice);
//		 
//		 //优惠券
//		 orderInfo.setCouponId(null);
//		 orderInfo.setCouponPrice(null);
//		 orderInfo.setParentId(null);
//		 
//		
//		 orderInfo.setColonelGroupId(colonelGroupId);
//		 
//		 orderMapper.insertSelective(orderInfo);
//         if (null == orderInfo.getId()) {
//            resultObj.put("errno", 1);
//            resultObj.put("errmsg", "订单提交失败");
//            return resultObj;
//         }
//         
//         //统计商品总价
//         for (Goods goodsItem : goodsList) {
//             Ordergoods ordergoods = new Ordergoods();
//             ordergoods.setOrderId(orderInfo.getId());
//             ordergoods.setGoodsId(goodsItem.getId());
//             ordergoods.setGoodsSn(goodsItem.getGoodsSn());
//             ordergoods.setProductId(null);
//             ordergoods.setGoodsName(goodsItem.getName());
//             ordergoods.setListPicUrl(goodsItem.getListPicUrl());
//             ordergoods.setMarketPrice(goodsItem.getMarketPrice());
//             ordergoods.setRetailPrice(goodsItem.getRetailPrice());
//             ordergoods.setNumber(goodsItem.getGoodsNumber());
//             ordergoods.setGoodsSpecifitionNameValue(null);
//             ordergoods.setGoodsSpecifitionIds(null);
//             ordergoodsMapper.insertSelective(ordergoods);
//         }
//
//         
//         resultObj.put("errno", 0);
//         resultObj.put("errmsg", "订单提交成功");
//         Map<String, Order> orderInfoMap = new HashMap<String, Order>();
//         orderInfoMap.put("orderInfo", orderInfo);
//         resultObj.put("data", orderInfoMap);
//         return resultObj;
//	}
	
	
	@Override
	public Order queryBySn(String out_trade_no){
		OrderExample oe = new OrderExample();
		Criteria c = oe.createCriteria();
		c.andOrderSnEqualTo(out_trade_no);
		List<Order> list = orderMapper.selectByExample(oe);
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	@Override
    public List<Order> queryListWithGoods(Order order, int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
        return orderMapper.selectWithGoods(order);
    }
	
	@Override
	public Order queryWithGoods(Integer orderId){
		Order order = new Order();
		order.setId(orderId);
		List<Order> list  = orderMapper.selectWithGoods(order);
		if(list.size()!=0)
			return list.get(0);
		else 
			return null;
		
	}
}
