package com.fresh.manager.pojo.shop;

import java.math.BigDecimal;

import com.fresh.manager.pojo.BaseDTO;

public class Brand  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 图片
     */
    private String listPicUrl;

    /**
     * 描述
     */
    private String simpleDesc;

    /**
     * 图片
     */
    private String picUrl;

    /**
     * 排序
     */
    private Short sortOrder;

    /**
     * 显示
     */
    private Boolean isShow;

    /**
     * 
     */
    private BigDecimal floorPrice;

    /**
     * app显示图片
     */
    private String appListPicUrl;

    /**
     * 新品牌
     */
    private Boolean isNew;

    /**
     * 图片
     */
    private String newPicUrl;

    /**
     * 排序
     */
    private Short newSortOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl == null ? null : listPicUrl.trim();
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc == null ? null : simpleDesc.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    public String getAppListPicUrl() {
        return appListPicUrl;
    }

    public void setAppListPicUrl(String appListPicUrl) {
        this.appListPicUrl = appListPicUrl == null ? null : appListPicUrl.trim();
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getNewPicUrl() {
        return newPicUrl;
    }

    public void setNewPicUrl(String newPicUrl) {
        this.newPicUrl = newPicUrl == null ? null : newPicUrl.trim();
    }

    public Short getNewSortOrder() {
        return newSortOrder;
    }

    public void setNewSortOrder(Short newSortOrder) {
        this.newSortOrder = newSortOrder;
    }
}