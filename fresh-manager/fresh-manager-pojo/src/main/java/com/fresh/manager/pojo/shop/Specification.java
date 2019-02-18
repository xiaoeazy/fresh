package com.fresh.manager.pojo.shop;

import com.fresh.manager.pojo.BaseDTO;

public class Specification extends BaseDTO{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 规范名称
     */
    private String name;

    /**
     * 排序
     */
    private Byte sortOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
    }
}