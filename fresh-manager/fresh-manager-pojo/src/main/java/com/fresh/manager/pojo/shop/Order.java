package com.fresh.manager.pojo.shop;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.fresh.manager.pojo.BaseDTO;

public class Order extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer id;

    /**
     * 订单序列号
     */
    private String orderSn;

    /**
     * 会员Id
     */
    private Integer userId;

    /**
     *  1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除     2xx 表示订单支付状态　201订单已付款，等待发货     3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货     4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货
     */
    private Short orderStatus;

    /**
     * 发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货
     */
    private Short shippingStatus;

    /**
     * 付款状态 支付状态;0未付款;1付款中;2已付款;4退款
     */
    private Short payStatus;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 地市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 补充说明
     */
    private String postscript;

    /**
     * 快递公司Id
     */
    private Short shippingId;

    /**
     * 快递公司名称
     */
    private String shippingName;

    /**
     * 付款
     */
    private String payId;

    /**
     * 
     */
    private String payName;

    /**
     * 快递费用
     */
    private BigDecimal shippingFee;

    /**
     * 实际需要支付的金额
     */
    private BigDecimal actualPrice;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integralMoney;

    /**
     * 订单总价
     */
    private BigDecimal orderPrice;

    /**
     * 商品总价
     */
    private BigDecimal goodsPrice;

    /**
     * 新增时间
     */
    private Date addTime;

    /**
     * 确认时间
     */
    private Date confirmTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 配送费用
     */
    private Integer freightPrice;

    /**
     * 使用的优惠券id
     */
    private Integer couponId;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 优惠价格
     */
    private BigDecimal couponPrice;

    /**
     * 
     */
    private String callbackStatus;

    /**
     * 快递号
     */
    private String shippingNo;

    /**
     * 订单满减
     */
    private BigDecimal fullCutPrice;

    /**
     * //订单类型 1：普通订单 2：团购订单 3：砍价订单 4: 直接购买 5:团长开团
     */
    private Short orderType;
    
    
    
    @Transient
    private String userName;
    
    

    @Transient
    private List<Ordergoods> orderGoodsList;
    @Transient
    private String orderStatusText;
    @Transient
    private Map handleOption; //可操作的选项
    
    

    public List<Ordergoods> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<Ordergoods> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}

	public String getOrderStatusText() {
		 if (null != orderStatus && StringUtils.isEmpty(orderStatusText)) {
	            orderStatusText = "未付款";
	            switch (orderStatus) {
	                case 0:
	                    orderStatusText = "未付款";
	                    break;
	                case 201:
	                    orderStatusText = "等待发货";
	                    break;
	                case 300:
	                    orderStatusText = "待收货";
	                    break;
	                case 301:
	                    orderStatusText = "已完成";
	                    break;
	                case 200:
	                    orderStatusText = "已付款";
	                    break;
	                case 101:
	                    orderStatusText = "已取消";
	                    break;
	                case 401:
	                    orderStatusText = "已取消";
	                    break;
	                case 402:
	                    orderStatusText = "已退货";
	                    break;
	            }
	        }
	        return orderStatusText;
	}

	public void setOrderStatusText(String orderStatusText) {
		this.orderStatusText = orderStatusText;
	}

	public Map getHandleOption() {
		 handleOption = new HashMap();
	        handleOption.put("cancel", false);//取消操作
	        handleOption.put("delete", false);//删除操作
	        handleOption.put("pay", false);//支付操作
	        handleOption.put("comment", false);//评论操作
	        handleOption.put("delivery", false);//确认收货操作
	        handleOption.put("confirm", false);//完成订单操作
	        handleOption.put("return", false); //退换货操作
	        handleOption.put("buy", false); //再次购买

	        //订单流程：　下单成功－》支付订单－》发货－》收货－》评论
	        //订单相关状态字段设计，采用单个字段表示全部的订单状态
	        //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
	        //2xx 表示订单支付状态　201订单已付款，等待发货
	        //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
	        //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货

	        //如果订单已经取消或是已完成，则可删除和再次购买
	        if (orderStatus == 101) {
//	            handleOption.put("delete", true);
	            handleOption.put("buy", true);
	        }

	        //如果订单没有被取消，且没有支付，则可支付，可取消
	        if (orderStatus == 0) {
	            handleOption.put("cancel", true);
	            handleOption.put("pay", true);
	        }

	        //如果订单已付款，没有发货，则可退款操作
	        if (orderStatus == 201) {
	            handleOption.put("cancel", true);
	        }

	        //如果订单已经发货，没有收货，则可收货操作和退款、退货操作
	        if (orderStatus == 300) {
//	            handleOption.put("cancel", true);
	            handleOption.put("confirm", true);
//	            handleOption.put("return", true);
	        }

	        //如果订单已经支付，且已经收货，则可完成交易、评论和再次购买
	        if (orderStatus == 301) {
	            handleOption.put("comment", true);
	            handleOption.put("buy", true);
	        }
	        return handleOption;
	}

	public void setHandleOption(Map handleOption) {
		this.handleOption = handleOption;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
     * 
     */
    private Integer colonelGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Short getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Short shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Short getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Short payStatus) {
        this.payStatus = payStatus;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript == null ? null : postscript.trim();
    }

    public Short getShippingId() {
        return shippingId;
    }

    public void setShippingId(Short shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Integer freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getCallbackStatus() {
        return callbackStatus;
    }

    public void setCallbackStatus(String callbackStatus) {
        this.callbackStatus = callbackStatus == null ? null : callbackStatus.trim();
    }

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo == null ? null : shippingNo.trim();
    }

    public BigDecimal getFullCutPrice() {
        return fullCutPrice;
    }

    public void setFullCutPrice(BigDecimal fullCutPrice) {
        this.fullCutPrice = fullCutPrice;
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public Integer getColonelGroupId() {
        return colonelGroupId;
    }

    public void setColonelGroupId(Integer colonelGroupId) {
        this.colonelGroupId = colonelGroupId;
    }
}