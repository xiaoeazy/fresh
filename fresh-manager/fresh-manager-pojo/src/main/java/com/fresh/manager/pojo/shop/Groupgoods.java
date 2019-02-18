package com.fresh.manager.pojo.shop;

import javax.persistence.Transient;

public class Groupgoods {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer groupId;

    /**
     * 
     */
    private Integer goodsId;
    
    
    @Transient
    private Goods goods;
    
    

    public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}