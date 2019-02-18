package com.fresh.api.service;

import java.util.Map;

import com.fresh.api.exception.CommonException;
import com.fresh.manager.pojo.shop.User;

public interface IApiPayService {

    public Map<Object, Object> payPrepay(User loginUser,Integer orderId,String clientIp) throws CommonException;

	public void orderQuery(User loginUser, Integer orderId) throws CommonException;

	public void notify(String reponseXml);
	
	public Boolean refund(Integer orderId)throws  CommonException;
    
}
