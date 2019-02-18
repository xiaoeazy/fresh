package com.fresh.manager.pojo.shop;

import javax.persistence.Transient;

import com.fresh.manager.pojo.BaseDTO;

public class Attribute  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 商品类型
     */
    private Integer attributeCategoryId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入
     */
    private Boolean inputType;

    /**
     * 
     */
    private Byte sortOrder;

    /**
     * 即选择输入,则attr_name对应的值的取值就是该这字段值 
     */
    private String value;
    
    
    @Transient
    private String categoryName;
    
    
    

    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public void setAttributeCategoryId(Integer attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getInputType() {
        return inputType;
    }

    public void setInputType(Boolean inputType) {
        this.inputType = inputType;
    }

    public Byte getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}