package com.fresh.manager.pojo.shop;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public class Group {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String groupName;

    /**
     * 
     */
    private String groupSn;

    /**
     * 
     */
    private Boolean isDelete;

    /**
     * 
     */
    private Long createUserId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String groupDesc;
    
    
    
    @Transient
    private List<Goods> goodsList;
    
    

    public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupSn() {
        return groupSn;
    }

    public void setGroupSn(String groupSn) {
        this.groupSn = groupSn == null ? null : groupSn.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }
}