package com.fresh.manager.pojo.shop;

import java.util.Date;

import com.fresh.manager.pojo.BaseDTO;

public class Feedback  extends BaseDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer msgId;

    /**
     * 会员Id
     */
    private Integer userId;

    /**
     * 会员会员名称
     */
    private String userName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 反馈类型
     */
    private Short feedType;

    /**
     * 状态
     */
    private Short status;

    /**
     * 反馈时间
     */
    private Date addTime;

    /**
     * 详细内容
     */
    private String content;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Short getFeedType() {
        return feedType;
    }

    public void setFeedType(Short feedType) {
        this.feedType = feedType;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}