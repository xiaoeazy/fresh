package com.fresh.api.pojo;

import java.math.BigDecimal;

public class Product {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品Id
     */
    private Integer goodsId;

    /**
     * 商品规格ids
     */
    private String goodsSpecificationIds;

    /**
     * 商品序列号
     */
    private String goodsSn;

    /**
     * 商品编码
     */
    private Integer goodsNumber;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 价格
     */
    private BigDecimal marketPrice;

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

    public String getGoodsSpecificationIds() {
        return goodsSpecificationIds;
    }

    public void setGoodsSpecificationIds(String goodsSpecificationIds) {
        this.goodsSpecificationIds = goodsSpecificationIds == null ? null : goodsSpecificationIds.trim();
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
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