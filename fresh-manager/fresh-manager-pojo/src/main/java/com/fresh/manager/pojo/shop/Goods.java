package com.fresh.manager.pojo.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.fresh.manager.pojo.BaseDTO;

public class Goods  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer id;

    /**
     * 商品类型Id
     */
    private Integer categoryId;

    /**
     * 
     */
    private String goodsSn;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer brandId;

    /**
     * 商品库存
     */
    private Integer goodsNumber;

    /**
     * 
     */
    private String keywords;

    /**
     * 
     */
    private String goodsBrief;

    /**
     * 
     */
    private Boolean isOnSale;

    /**
     * 
     */
    private Date addTime;

    /**
     * 
     */
    private Short sortOrder;

    /**
     * 
     */
    private Boolean isDelete;

    /**
     * 属性类别
     */
    private Integer attributeCategory;

    /**
     * 专柜价格
     */
    private BigDecimal counterPrice;

    /**
     * 附加价格
     */
    private BigDecimal extraPrice;

    /**
     * 是否新商品
     */
    private Boolean isNew;

    /**
     * 商品单位
     */
    private String goodsUnit;

    /**
     * 商品主图
     */
    private String primaryPicUrl;

    /**
     * 商品列表图
     */
    private String listPicUrl;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 销售量
     */
    private Integer sellVolume;

    /**
     * 主sku　product_id
     */
    private Integer primaryProductId;

    /**
     * 单位价格，单价
     */
    private BigDecimal unitPrice;

    /**
     * 推广描述
     */
    private String promotionDesc;

    /**
     * 推广标签
     */
    private String promotionTag;

    /**
     * APP专享价
     */
    private BigDecimal appExclusivePrice;

    /**
     * 是否是APP专属
     */
    private Boolean isAppExclusive;

    /**
     * 限购
     */
    private Boolean isLimited;

    /**
     * 热销
     */
    private Boolean isHot;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 修改人ID
     */
    private Long updateUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 
     */
    private Long createUserDeptId;

    /**
     * 
     */
    private String goodsDesc;
    
    
    @Transient
    private String categoryName;
    @Transient
    private String attributeCategoryName;
    @Transient
    private String brandName;
    
    @Transient
    List<Goodsattribute> attributeList = new ArrayList<Goodsattribute>();
    @Transient
    List<Goodsgallery> goodsImgList = new ArrayList<Goodsgallery>();

    
    
    
   

	public List<Goodsattribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<Goodsattribute> attributeList) {
		this.attributeList = attributeList;
	}

	public List<Goodsgallery> getGoodsImgList() {
		return goodsImgList;
	}

	public void setGoodsImgList(List<Goodsgallery> goodsImgList) {
		this.goodsImgList = goodsImgList;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAttributeCategoryName() {
		return attributeCategoryName;
	}

	public void setAttributeCategoryName(String attributeCategoryName) {
		this.attributeCategoryName = attributeCategoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief == null ? null : goodsBrief.trim();
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(Integer attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }

    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl == null ? null : primaryPicUrl.trim();
    }

    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl == null ? null : listPicUrl.trim();
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Integer sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Integer getPrimaryProductId() {
        return primaryProductId;
    }

    public void setPrimaryProductId(Integer primaryProductId) {
        this.primaryProductId = primaryProductId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc == null ? null : promotionDesc.trim();
    }

    public String getPromotionTag() {
        return promotionTag;
    }

    public void setPromotionTag(String promotionTag) {
        this.promotionTag = promotionTag == null ? null : promotionTag.trim();
    }

    public BigDecimal getAppExclusivePrice() {
        return appExclusivePrice;
    }

    public void setAppExclusivePrice(BigDecimal appExclusivePrice) {
        this.appExclusivePrice = appExclusivePrice;
    }

    public Boolean getIsAppExclusive() {
        return isAppExclusive;
    }

    public void setIsAppExclusive(Boolean isAppExclusive) {
        this.isAppExclusive = isAppExclusive;
    }

    public Boolean getIsLimited() {
        return isLimited;
    }

    public void setIsLimited(Boolean isLimited) {
        this.isLimited = isLimited;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserDeptId() {
        return createUserDeptId;
    }

    public void setCreateUserDeptId(Long createUserDeptId) {
        this.createUserDeptId = createUserDeptId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }
}