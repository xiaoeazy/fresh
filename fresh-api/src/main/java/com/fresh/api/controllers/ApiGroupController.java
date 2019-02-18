package com.fresh.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.IgnoreAuth;
import com.fresh.api.pojo.Group;
import com.fresh.api.service.IApiGroupService;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.api.util.ApiPageUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/group")
public class ApiGroupController  extends ApiBaseAction {
	
	@Autowired
	private IApiGroupService apiGroupService;
	
	
	
	  /**
     * 查看列表
     */
    @ApiOperation(value = "获取团购列表")
    @IgnoreAuth
    @RequestMapping(value = "/list")
    public Object list(Group group,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {

        List<Group> groupList = apiGroupService.queryListWithGoods(group, page, size);
        int total = apiGroupService.queryTotal(group);

        ApiPageUtils groupData = new ApiPageUtils(groupList, total, size, page);
        return toResponsSuccess(groupData);
    }
    
    @ApiOperation(value = "获取团购列表单个")
    @IgnoreAuth
    @RequestMapping(value = "/query")
    public Object query(Integer id) {
    	Group vo = new Group();
    	vo.setId(id);
        Group group = apiGroupService.queryListWithGoodsByGroupId(vo);
        return toResponsSuccess(group);
    }
	
}
