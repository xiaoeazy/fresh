package com.fresh.manager.pojo.shop;

import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.fresh.manager.pojo.BaseDTO;

public class Address  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer id;

    /**
     * 会员ID
     */
    private Integer userId;

    /**
     * 收货人姓名
     */
    private String userName;

    /**
     * 手机
     */
    private String telNumber;

    /**
     * 邮编
     */
    private String postalCode;

    /**
     * 收货地址国家码
     */
    private String nationalCode;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区
     */
    private String countyName;

    /**
     * 详细收货地址信息
     */
    private String detailInfo;

    /**
     * 
     */
    private Boolean isDefault=false;
    
    @Transient
    private String shopUserName;   //用户表名称
    
    @Transient
    private String full_region;
    
    public void setFull_region(String full_region) {
        this.full_region = full_region;
    }
    
    public String getFull_region() {
        if (StringUtils.isEmpty(full_region)) {
            full_region = getProvinceName() + getCityName() + getCountyName();
        }
        return full_region;
    }
    
    public String getShopUserName() {
		return shopUserName;
	}

	public void setShopUserName(String shopUserName) {
		this.shopUserName = shopUserName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber == null ? null : telNumber.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode == null ? null : nationalCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName == null ? null : countyName.trim();
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo == null ? null : detailInfo.trim();
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
    
    public Address defaultAddress(List<Address> list){
    	Address address= null;
    	for(int i =0;i<list.size();i++){
    		Address a = list.get(i);
    		if(i==0){
    			address = a;
    		}else{
    			if(a.isDefault==true){
        			address = a ;
        			break;
        		}
    		}
    	}
    	return address;
    }
}