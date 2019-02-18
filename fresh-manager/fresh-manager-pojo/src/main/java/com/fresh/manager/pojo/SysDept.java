package com.fresh.manager.pojo;

import java.util.List;

import javax.persistence.Transient;

public class SysDept  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Long deptId;

    /**
     * 上级部门ID，一级部门为0
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Short delFlag;
    
    
    @Transient
    private Boolean open;
    @Transient
    private List<Long> deptFilter;
    
    
    
    

    public List<Long> getDeptFilter() {
		return deptFilter;
	}

	public void setDeptFilter(List<Long> deptFilter) {
		this.deptFilter = deptFilter;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }
}