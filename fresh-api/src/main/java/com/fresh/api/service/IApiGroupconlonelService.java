package com.fresh.api.service;

import java.util.List;

import com.fresh.api.pojo.Groupcolonel;
import com.fresh.manager.pojo.shop.User;

public interface IApiGroupconlonelService {

	void save(User loginUser,Groupcolonel g);

	List<Groupcolonel> queryList(Groupcolonel groupcolonel, int pageNum, int pageSize);

	
	int queryTotal(Groupcolonel groupcolonel);

	Groupcolonel queryById(int colonelGroupId);
    
    
}
