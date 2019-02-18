package com.fresh.manager.pojo.shop;

public class Adposition {
    /**
     * 
     */
    private Integer id;

    /**
     * 位置名称
     */
    private String name;

    /**
     * 宽度
     */
    private Short width;

    /**
     * 高度
     */
    private Short height;

    /**
     * 
     */
    private String descript;

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

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }
}