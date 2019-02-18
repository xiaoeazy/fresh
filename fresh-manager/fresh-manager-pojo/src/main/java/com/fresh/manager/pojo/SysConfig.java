package com.fresh.manager.pojo;

import org.hibernate.validator.constraints.NotBlank;

public class SysConfig   extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Long id;

    /**
     * key
     */
    @NotBlank(message = "参数名不能为空")
    private String name;

    /**
     * value
     */
    @NotBlank(message = "参数值不能为空")
    private String value;

    /**
     * 状态   0：隐藏   1：显示
     */
    private Short status;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}