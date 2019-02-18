package com.fresh.api.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.api.exception.CommonException;
import com.fresh.api.service.IApiPayService;
import com.fresh.api.util.wechat.WechatRefundApiResult;
import com.fresh.api.util.wechat.WechatUtil;
import com.fresh.common.cache.J2CacheComponent;
import com.fresh.common.cache.service.JedisClient;
import com.fresh.common.utils.CharUtil;
import com.fresh.common.utils.DateUtils;
import com.fresh.common.utils.MapUtils;
import com.fresh.common.utils.ResourceUtil;
import com.fresh.common.utils.XmlUtil;
import com.fresh.manager.pojo.shop.Order;
import com.fresh.manager.pojo.shop.Ordergoods;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IOrderGoodsService;
import com.fresh.manager.shop.service.IOrderService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApiPayServiceImpl implements IApiPayService {
	  private Logger logger = Logger.getLogger(getClass());
	@Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private JedisClient jedisClient;
	
    @Override
	public Map<Object, Object> payPrepay(User loginUser,Integer orderId,String clientIp) throws CommonException{
		Order orderInfo = orderService.queryById(orderId);

        if (null == orderInfo) {
            throw new CommonException("订单已取消");
        }

        if (orderInfo.getPayStatus() != 0) {
        	throw new CommonException("订单已支付，请不要重复操作");
        }

        String nonceStr = CharUtil.getRandomString(32);

        //https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=3
        Map<Object, Object> resultObj = new TreeMap();
        try {
            Map<Object, Object> parame = new TreeMap<Object, Object>();
            parame.put("appid", ResourceUtil.getConfigByName("wx.appId"));
            // 商家账号。
            parame.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
            String randomStr = CharUtil.getRandomNum(18).toUpperCase();
            // 随机字符串
            parame.put("nonce_str", randomStr);
            // 商户订单编号
            parame.put("out_trade_no", orderInfo.getOrderSn());
            // 商品描述
            parame.put("body", "商品-支付");
            //订单的商品
            List<Ordergoods> orderGoods = orderGoodsService.queryByOrderId(orderId);
            if (null != orderGoods) {
                String body = "商品-";
                for (Ordergoods goodsVo : orderGoods) {
                    body = body + goodsVo.getGoodsName() + "、";
                }
                if (body.length() > 0) {
                    body = body.substring(0, body.length() - 1);
                }
                // 商品描述
                parame.put("body", body);
            }
            //支付金额
            parame.put("total_fee", orderInfo.getActualPrice().multiply(new BigDecimal(100)).intValue());
            // 回调地址
            parame.put("notify_url", ResourceUtil.getConfigByName("wx.notifyUrl"));
            // 交易类型APP
            parame.put("trade_type", ResourceUtil.getConfigByName("wx.tradeType"));
            parame.put("spbill_create_ip", clientIp);
            parame.put("openid", loginUser.getWeixinOpenid());
            String sign = WechatUtil.arraySign(parame, ResourceUtil.getConfigByName("wx.paySignKey"));
            // 数字签证
            parame.put("sign", sign);

            String xml = MapUtils.convertMap2Xml(parame);
            logger.info("xml:" + xml);
            Map<String, Object> resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.uniformorder"), xml));
            // 响应报文
            String return_code = MapUtils.getString("return_code", resultUn);
            String return_msg = MapUtils.getString("return_msg", resultUn);
            //
            if (return_code.equalsIgnoreCase("FAIL")) {
                throw new CommonException("支付失败," + return_msg);
            } else if (return_code.equalsIgnoreCase("SUCCESS")) {
                // 返回数据
                String result_code = MapUtils.getString("result_code", resultUn);
                String err_code_des = MapUtils.getString("err_code_des", resultUn);
                if (result_code.equalsIgnoreCase("FAIL")) {
                    throw new CommonException("支付失败," + err_code_des);
                } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                    String prepay_id = MapUtils.getString("prepay_id", resultUn);
                    // 先生成paySign 参考https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=5
                    resultObj.put("appId", ResourceUtil.getConfigByName("wx.appId"));
                    resultObj.put("timeStamp", DateUtils.timeToStr(System.currentTimeMillis() / 1000, DateUtils.DATE_TIME_PATTERN));
                    resultObj.put("nonceStr", nonceStr);
                    resultObj.put("package", "prepay_id=" + prepay_id);
                    resultObj.put("signType", "MD5");
                    String paySign = WechatUtil.arraySign(resultObj, ResourceUtil.getConfigByName("wx.paySignKey"));
                    resultObj.put("paySign", paySign);
                    // 业务处理
                    orderInfo.setPayId(prepay_id);
                    // 付款中
                    orderInfo.setPayStatus((short) 1);
                    orderService.updateByPrimaryKeySelective(orderInfo);
                   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        return resultObj;
	}

	@Override
	public void orderQuery(User loginUser, Integer orderId) throws CommonException{
		if (orderId == null) {
            throw new  CommonException("订单不存在");
        }

        Order orderDetail = orderService.queryById(orderId);
        Map<Object, Object> parame = new TreeMap<Object, Object>();
        parame.put("appid", ResourceUtil.getConfigByName("wx.appId"));
        // 商家账号。
        parame.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
        String randomStr = CharUtil.getRandomNum(18).toUpperCase();
        // 随机字符串
        parame.put("nonce_str", randomStr);
        // 商户订单编号
        parame.put("out_trade_no", orderDetail.getOrderSn());

        String sign = WechatUtil.arraySign(parame, ResourceUtil.getConfigByName("wx.paySignKey"));
        // 数字签证
        parame.put("sign", sign);

        String xml = MapUtils.convertMap2Xml(parame);
        logger.info("xml:" + xml);
        Map<String, Object> resultUn = null;
        try {
            resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.orderquery"), xml));
        } catch (Exception e) {
            e.printStackTrace();
            throw new  CommonException( e.getMessage());
        }
        // 响应报文
        String return_code = MapUtils.getString("return_code", resultUn);
        String return_msg = MapUtils.getString("return_msg", resultUn);

        if (!"SUCCESS".equals(return_code)) {
            throw new  CommonException(return_msg);
        }

        String trade_state = MapUtils.getString("trade_state", resultUn);
        if ("SUCCESS".equals(trade_state)) {
            // 更改订单状态
            // 业务处理
            Order orderInfo = new Order();
            orderInfo.setId(orderId);
            orderInfo.setPayStatus((short) 2);
            orderInfo.setOrderStatus((short) 201);
            orderInfo.setShippingStatus((short) 0);
            orderInfo.setPayTime(new Date());
            orderService.updateByPrimaryKeySelective(orderInfo);
            
        } else if ("USERPAYING".equals(trade_state)) {
            // 重新查询 正在支付中
            Integer num = Integer.parseInt(jedisClient.hget(J2CacheComponent.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + ""));
            if (num == null) {
            	jedisClient.hset(J2CacheComponent.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "", "1");
                this.orderQuery(loginUser, orderId);
            } else if (num <= 3) {
            	num =num+1;
            	jedisClient.hset(J2CacheComponent.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "", num+"");
                this.orderQuery(loginUser, orderId);
            } else {
            	jedisClient.hdel(J2CacheComponent.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "");
            	 throw new  CommonException(trade_state);
            }
        } else {
            // 失败
        	 throw new  CommonException(trade_state);
        }

        throw new  CommonException("未知错误");
	}
	
	@Override
	public void notify(String reponseXml){
            WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WechatRefundApiResult.class);
            String result_code = result.getResult_code();
            if (result_code.equalsIgnoreCase("FAIL")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付失败");
               
            } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付成功");
                // 业务处理
                Order orderInfo = orderService.queryBySn(out_trade_no);
                orderInfo.setPayStatus((short) 2);
                orderInfo.setOrderStatus((short) 201);
                orderInfo.setShippingStatus((short) 0);
                orderInfo.setPayTime(new Date());
                orderService.updateByPrimaryKeySelective(orderInfo);
            }
	}
	
	@Override
	public Boolean refund(Integer orderId) throws  CommonException{
		 	Order orderInfo = orderService.queryById(orderId);

	        if (null == orderInfo) {
	        	throw new  CommonException("订单已取消");
	        }

	        if (orderInfo.getOrderStatus() == 401 || orderInfo.getOrderStatus() == 402) {
	        	throw new  CommonException("订单已退款");
	        }

	        if (orderInfo.getPayStatus() < 2) {
	        	throw new  CommonException("订单未付款，不能退款");
	        }

//	        WechatRefundApiResult result = WechatUtil.wxRefund(orderInfo.getId().toString(),
//	                orderInfo.getActual_price().doubleValue(), orderInfo.getActual_price().doubleValue());
	        WechatRefundApiResult result = WechatUtil.wxRefund(orderInfo.getId().toString(),
	                10.01, 10.01);
	        if (result.getResult_code().equals("SUCCESS")) {
	            if (orderInfo.getOrderStatus() == 201) {
	                orderInfo.setOrderStatus((short) 401);
	            } else if (orderInfo.getOrderStatus() == 300) {
	                orderInfo.setOrderStatus((short) 402);
	            }
	            orderService.updateByPrimaryKeySelective(orderInfo);
	           return true;
	        } else {
	           return false;
	        }
	}
}
