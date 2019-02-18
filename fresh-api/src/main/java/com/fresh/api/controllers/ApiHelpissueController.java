package com.fresh.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.IgnoreAuth;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.manager.pojo.shop.Helpissue;
import com.fresh.manager.pojo.shop.Helptype;
import com.fresh.manager.shop.service.IHelpissueService;
import com.fresh.manager.shop.service.IHelptypeService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/helpissue")
public class ApiHelpissueController  extends ApiBaseAction {
	@Autowired
	private IHelptypeService apiHelpTypeService;
	@Autowired
	private IHelpissueService apiHelpissueService;
	
    @ApiOperation(value = "获取所有列表")
    @IgnoreAuth
    @RequestMapping(value = "/typeList")
    public Object typeList() {
        List<Helptype> helptypeList = apiHelpTypeService.queryAllType();
        return toResponsSuccess(helptypeList);
    }
	 
    @ApiOperation(value = "获取所有列表")
    @IgnoreAuth
    @RequestMapping(value = "/issueList")
    public Object issueList(Helpissue helpissue) {
        List<Helpissue> HelpissueList = apiHelpissueService.query(helpissue);
        return toResponsSuccess(HelpissueList);
    }
	 
	
}
