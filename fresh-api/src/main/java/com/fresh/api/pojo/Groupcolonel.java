package com.fresh.api.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

public class Groupcolonel {
    /**
     * 
     */
    private Integer colonelGroupId;

    /**
     * //0 自提  1 送货上门
     */
    private Short colonelGroupType;

    /**
     * 开团序列号
     */
    private String colonelGroupSn;

    /**
     * 
     */
    private String colonelGroupName;

    /**
     * 
     */
    private Integer groupId;

    /**
     * 
     */
    private Date groupStartTime;

    /**
     * 
     */
    private Date groupEndTime;

    /**
     * 团购发货时间 只有type=1送货上门才有值
     */
    private Date groupDeliveryTime;

    /**
     * 
     */
    private String groupAddress;

    /**
     * 
     */
    private BigDecimal latitude;

    /**
     * 
     */
    private BigDecimal longitude;

    /**
     * 
     */
    private Date pickUpTime;

    /**
     * 说明
     */
    private String colonelGroupContent;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Boolean state;
    
    @Transient
    private Boolean overdue ;
    
    @Transient 
    private Group group;
    
    

    public Boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Integer getColonelGroupId() {
        return colonelGroupId;
    }

    public void setColonelGroupId(Integer colonelGroupId) {
        this.colonelGroupId = colonelGroupId;
    }

    public Short getColonelGroupType() {
        return colonelGroupType;
    }

    public void setColonelGroupType(Short colonelGroupType) {
        this.colonelGroupType = colonelGroupType;
    }

    public String getColonelGroupSn() {
        return colonelGroupSn;
    }

    public void setColonelGroupSn(String colonelGroupSn) {
        this.colonelGroupSn = colonelGroupSn == null ? null : colonelGroupSn.trim();
    }

    public String getColonelGroupName() {
        return colonelGroupName;
    }

    public void setColonelGroupName(String colonelGroupName) {
        this.colonelGroupName = colonelGroupName == null ? null : colonelGroupName.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getGroupStartTime() {
        return groupStartTime;
    }

    public void setGroupStartTime(Date groupStartTime) {
        this.groupStartTime = groupStartTime;
    }

    public Date getGroupEndTime() {
        return groupEndTime;
    }

    public void setGroupEndTime(Date groupEndTime) {
        this.groupEndTime = groupEndTime;
    }

    public Date getGroupDeliveryTime() {
        return groupDeliveryTime;
    }

    public void setGroupDeliveryTime(Date groupDeliveryTime) {
        this.groupDeliveryTime = groupDeliveryTime;
    }

    public String getGroupAddress() {
        return groupAddress;
    }

    public void setGroupAddress(String groupAddress) {
        this.groupAddress = groupAddress == null ? null : groupAddress.trim();
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getColonelGroupContent() {
        return colonelGroupContent;
    }

    public void setColonelGroupContent(String colonelGroupContent) {
        this.colonelGroupContent = colonelGroupContent == null ? null : colonelGroupContent.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}