package com.fresh.api.pojo;

import javax.persistence.Transient;

public class SysRegion {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 类型 0国家 1省份 2地市 3区县 4街道
     */
    private Integer type;

    /**
     * 区域代理Id
     */
    private Integer agencyId;
    
    
  //父级名称
    @Transient
    private String parentName;
    
    
    
    

    public SysRegion() {
    	super();
    }
    
    public SysRegion(SysRegion sysRegion) {
        this.id = sysRegion.getId();
        this.parentId = sysRegion.getParentId();
        this.name = sysRegion.getName();
        this.type = sysRegion.getType();
        this.agencyId = sysRegion.getAgencyId();
    }

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
}