package com.fresh.manager.pojo.shop;

public class Helptype {
    /**
     * 
     */
    private Integer id;

    /**
     * 问题分类
     */
    private String typeName;

    /**
     * 排序
     */
    private Short sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }
}