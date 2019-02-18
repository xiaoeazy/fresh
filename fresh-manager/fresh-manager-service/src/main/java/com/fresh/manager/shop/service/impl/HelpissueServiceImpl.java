package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.HelpissueMapper;
import com.fresh.manager.pojo.shop.Helpissue;
import com.fresh.manager.pojo.shop.HelpissueExample;
import com.fresh.manager.pojo.shop.HelpissueExample.Criteria;
import com.fresh.manager.shop.service.IHelpissueService;

@Service
@Transactional(rollbackFor = Exception.class)
public class HelpissueServiceImpl implements IHelpissueService {
	@Autowired
	private HelpissueMapper helpissueMapper;
	
	@Override
	public List<Helpissue>  query(Helpissue helpissue){
		HelpissueExample hs = new HelpissueExample();
		Criteria cr = hs.createCriteria();
		cr.andTypeIdEqualTo(helpissue.getTypeId());
		return helpissueMapper.selectByExample(hs);
	}
	
}
