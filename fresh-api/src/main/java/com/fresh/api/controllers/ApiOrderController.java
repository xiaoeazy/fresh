package com.fresh.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.LoginUser;
import com.fresh.api.pojo.Goods;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.api.util.ApiPageUtils;
import com.fresh.manager.pojo.shop.Order;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IOrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/order")
public class ApiOrderController  extends ApiBaseAction {
	@Autowired
	private IOrderService orderService;
	
	/**
     * 获取订单列表
     */
    @ApiOperation(value = "订单提交")
    @PostMapping("/submit")
    public Object submit(@LoginUser User loginUser, Integer colonelGroupId,
			@RequestBody List<Goods> goodsList,Integer addressId) {
    	Map resultObj = null;
    	try {
//    		resultObj = orderService.createOrder(loginUser, colonelGroupId, goodsList, addressId);
			if (null != resultObj) {
	                return toResponsObject(MapUtils.getInteger(resultObj, "errno"), MapUtils.getString(resultObj, "errmsg"), resultObj.get("data"));
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return toResponsFail("提交失败");
    }
    
    /**
     * 获取订单列表
     */
    @ApiOperation(value = "获取订单列表")
    @PostMapping("/list")
    public Object list(@LoginUser User loginUser,
    		@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        Order order = new Order();
        order.setUserId(loginUser.getId());
        List<Order> orderEntityList = orderService.queryListWithGoods(order, page, size);
        int total = (int) orderService.queryTotal(order);
        ApiPageUtils pageUtil = new ApiPageUtils(orderEntityList, total, size, page);
        //
//        for (OrderVo item : orderEntityList) {
//            Map orderGoodsParam = new HashMap();
//            orderGoodsParam.put("order_id", item.getId());
//            //订单的商品
//            List<OrderGoodsVo> goodsList = orderGoodsService.queryList(orderGoodsParam);
//            Integer goodsCount = 0;
//            for (OrderGoodsVo orderGoodsEntity : goodsList) {
//                goodsCount += orderGoodsEntity.getNumber();
//                item.setGoodsCount(goodsCount);
//            }
//        }
        return toResponsSuccess(pageUtil);
    }

    /**
     * 获取订单详情
     */
    @ApiOperation(value = "获取订单详情")
    @PostMapping("detail")
    public Object detail(Integer orderId) {
        Map resultObj = new HashMap();
        //
        Order orderInfo = orderService.queryWithGoods(orderId);
        if (null == orderInfo) {
            return toResponsObject(400, "订单不存在", "");
        }
       
        //订单最后支付时间
        if (orderInfo.getOrderStatus() == 0) {
            // if (moment().subtract(60, 'minutes') < moment(orderInfo.add_time)) {
//            orderInfo.final_pay_time = moment("001234", "Hmmss").format("mm:ss")
            // } else {
            //     //超过时间不支付，更新订单状态为取消
            // }
        }

        //订单可操作的选择,删除，支付，收货，评论，退换货
        Map handleOption = orderInfo.getHandleOption();
        //
        resultObj.put("orderInfo", orderInfo);
        resultObj.put("orderGoods", orderInfo.getOrderGoodsList());
        resultObj.put("handleOption", handleOption);
//        if (!StringUtils.isEmpty(orderInfo.getShippingCode()) && !StringUtils.isEmpty(orderInfo.getShippingNo())) {
            // 快递
//            List Traces = apiKdniaoService.getOrderTracesByJson(orderInfo.getShipping_code(), orderInfo.getShipping_no());
//            resultObj.put("shippingList", Traces);
//        }
        return toResponsSuccess(resultObj);
    }
}
