package com.fresh.manager.pojo.shop;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Transient;

import com.fresh.manager.pojo.BaseDTO;

public class Productsku extends BaseDTO{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品Id
     */
    private Integer goodsId;

    /**
     * 商品序列号
     */
    private String skuSn;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 价格
     */
    private BigDecimal marketPrice;
    
    @Transient
    private String goodsName;
    
    @Transient
    private List<Productskuspecification> productskuspecificationList;
    
    
    
    
    

    public List<Productskuspecification> getProductskuspecificationList() {
		return productskuspecificationList;
	}

	public void setProductskuspecificationList(List<Productskuspecification> productskuspecificationList) {
		this.productskuspecificationList = productskuspecificationList;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSkuSn() {
        return skuSn;
    }

    public void setSkuSn(String skuSn) {
        this.skuSn = skuSn == null ? null : skuSn.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }
}