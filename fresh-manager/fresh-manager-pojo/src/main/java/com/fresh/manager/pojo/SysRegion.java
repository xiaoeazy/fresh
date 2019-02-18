package com.fresh.manager.pojo;

import javax.persistence.Transient;

public class SysRegion extends Tree<SysRegion>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
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
     * 类型 0国家 1省份 2地市 3区县
     */
    private Short type;

    /**
     * 区域代理Id
     */
    private Integer agencyId;
    
    
    @Transient
    private String parentName;
    
    
    

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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
}