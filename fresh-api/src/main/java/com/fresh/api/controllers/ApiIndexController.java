package com.fresh.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.IgnoreAuth;
import com.fresh.api.pojo.Group;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.api.util.ApiPageUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/test")
public class ApiIndexController  extends ApiBaseAction {
	
	  /**
   * 查看列表
   */
  @ApiOperation(value = "首页banner")
  @IgnoreAuth
  @RequestMapping(value = "/banner")
  public Object list(Group group,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {

      List<Group> groupList = adService.queryListWithGoods(group, page, size);
      int total = apiGroupService.queryTotal(group);

      ApiPageUtils groupData = new ApiPageUtils(groupList, total, size, page);
      return toResponsSuccess(groupData);
  }
}
