package com.fresh.manager.pojo.shop;

import com.fresh.manager.pojo.BaseDTO;

public class Userlevel  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Short id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}