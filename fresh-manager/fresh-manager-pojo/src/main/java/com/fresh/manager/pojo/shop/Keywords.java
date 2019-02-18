package com.fresh.manager.pojo.shop;

import com.fresh.manager.pojo.BaseDTO;

public class Keywords extends BaseDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 热销
     */
    private Boolean isHot;

    /**
     * 默认
     */
    private Boolean isDefault;

    /**
     * 显示
     */
    private Boolean isShow;

    /**
     * 排序
     */
    private Short sortOrder;

    /**
     * 关键词的跳转链接
     */
    private String schemeUrl;

    /**
     * 类型
     */
    private Short type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSchemeUrl() {
        return schemeUrl;
    }

    public void setSchemeUrl(String schemeUrl) {
        this.schemeUrl = schemeUrl == null ? null : schemeUrl.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}