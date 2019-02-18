package com.fresh.manager.pojo.shop;

import com.fresh.manager.pojo.Tree;

public class Category extends Tree<Category>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 描述
     */
    private String frontDesc;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Short sortOrder;

    /**
     * 首页展示
     */
    private Short showIndex;

    /**
     * 显示
     */
    private Boolean isShow;

    /**
     * banner图片
     */
    private String bannerUrl;

    /**
     * icon链接
     */
    private String iconUrl;

    /**
     * 图片
     */
    private String imgUrl;

    /**
     * 手机banner
     */
    private String wapBannerUrl;

    /**
     * 级别
     */
    private String level;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 
     */
    private String frontName;

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getFrontDesc() {
        return frontDesc;
    }

    public void setFrontDesc(String frontDesc) {
        this.frontDesc = frontDesc == null ? null : frontDesc.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Short getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(Short showIndex) {
        this.showIndex = showIndex;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getWapBannerUrl() {
        return wapBannerUrl;
    }

    public void setWapBannerUrl(String wapBannerUrl) {
        this.wapBannerUrl = wapBannerUrl == null ? null : wapBannerUrl.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName == null ? null : frontName.trim();
    }
}