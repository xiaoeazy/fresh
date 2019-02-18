package com.fresh.api.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.fresh.common.exception.RRException;

public class Util {

	
	public static boolean dateCompare(Date date1 ,Date date2){
		int res = date1.compareTo(date2);
    	if(res==1){
    		return true;
    	}
    	return false;
	}
	
	
	
	public static <T> T getObject(String value, Class<T> clazz) {
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}else{
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				throw new RRException("获取参数失败");
			}
		}
	}
}
