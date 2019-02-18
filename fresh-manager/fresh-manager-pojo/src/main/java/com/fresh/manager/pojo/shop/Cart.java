package com.fresh.manager.pojo.shop;

import java.math.BigDecimal;

import javax.persistence.Transient;

import com.fresh.manager.pojo.BaseDTO;

public class Cart extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 会员Id
     */
    private Integer userId;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * 商品Id
     */
    private Integer goodsId;

    /**
     * 商品序列号
     */
    private String goodsSn;

    /**
     * 产品Id
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 数量
     */
    private Short number;

    /**
     * product表对应的goods_specifition_ids
     */
    private String goodsSpecifitionIds;

    /**
     * 
     */
    private Boolean checked;

    /**
     * 商品图片
     */
    private String listPicUrl;

    /**
     * 规格属性组成的字符串，用来显示用
     */
    private String goodsSpecifitionNameValue;
    
    
    @Transient
    private String userName;//用户表用户名称
    
    

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public String getGoodsSpecifitionIds() {
        return goodsSpecifitionIds;
    }

    public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
        this.goodsSpecifitionIds = goodsSpecifitionIds == null ? null : goodsSpecifitionIds.trim();
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl == null ? null : listPicUrl.trim();
    }

    public String getGoodsSpecifitionNameValue() {
        return goodsSpecifitionNameValue;
    }

    public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
        this.goodsSpecifitionNameValue = goodsSpecifitionNameValue == null ? null : goodsSpecifitionNameValue.trim();
    }
}