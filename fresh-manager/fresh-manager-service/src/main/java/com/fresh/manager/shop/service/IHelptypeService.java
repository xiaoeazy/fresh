package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Helptype;

/**
 * Service接口
 *
 */
public interface IHelptypeService {
	/**
	 * 查询所有类型
	 * @return
	 */
	List<Helptype> queryAllType();
	
   
}
