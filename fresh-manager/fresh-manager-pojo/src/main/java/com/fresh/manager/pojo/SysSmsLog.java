package com.fresh.manager.pojo;

import java.util.Date;

import javax.persistence.Transient;

public class SysSmsLog {
    /**
     * 主键
     */
    private String id;

    /**
     * 操作人
     */
    private Long userId;

    /**
     * 必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    private String content;

    /**
     * 必填参数。手机号码。多个以英文逗号隔开
     */
    private String mobile;

    /**
     * 可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送
     */
    private Date stime;

    /**
     * 必填参数。用户签名
     */
    private String sign;

    /**
     * 必填参数。固定值 pt
     */
    private String type;

    /**
     * 可选参数。扩展码，用户定义扩展码，只能为数字
     */
    private String extno;

    /**
     * 1成功 0失败
     */
    private Integer sendStatus;

    /**
     * 发送编号
     */
    private String sendId;

    /**
     * 无效号码数
     */
    private Integer invalidNum;

    /**
     * 成功提交数
     */
    private Integer successNum;

    /**
     * 黑名单数
     */
    private Integer blackNum;

    /**
     * 返回消息
     */
    private String returnMsg;
    
    
    @Transient
    private String userName;
    
    

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno == null ? null : extno.trim();
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId == null ? null : sendId.trim();
    }

    public Integer getInvalidNum() {
        return invalidNum;
    }

    public void setInvalidNum(Integer invalidNum) {
        this.invalidNum = invalidNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getBlackNum() {
        return blackNum;
    }

    public void setBlackNum(Integer blackNum) {
        this.blackNum = blackNum;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }
}