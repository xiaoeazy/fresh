package com.fresh.manager.pojo.shop;

import java.util.Date;

import javax.persistence.Transient;

import com.fresh.manager.pojo.BaseDTO;

public class Ad  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer adPositionId;

    /**
     * 形式
     */
    private Short mediaType;

    /**
     * 广告名称
     */
    private String name;

    /**
     * 链接
     */
    private String link;

    /**
     * 内容
     */
    private String content;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态
     */
    private Boolean enabled;

    /**
     * 图片
     */
    private String imageUrl;
    
    
    @Transient
    private String adPositionName;
    
    

    public String getAdPositionName() {
		return adPositionName;
	}

	public void setAdPositionName(String adPositionName) {
		this.adPositionName = adPositionName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdPositionId() {
        return adPositionId;
    }

    public void setAdPositionId(Integer adPositionId) {
        this.adPositionId = adPositionId;
    }

    public Short getMediaType() {
        return mediaType;
    }

    public void setMediaType(Short mediaType) {
        this.mediaType = mediaType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }
}