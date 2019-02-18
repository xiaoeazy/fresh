package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Helpissue;

/**
 * Service接口
 *
 */
public interface IHelpissueService {

	/**
	 * 根据helpissue查询
	 * @param helpissue
	 * @return
	 */
	List<Helpissue> query(Helpissue helpissue);
	

   
}
