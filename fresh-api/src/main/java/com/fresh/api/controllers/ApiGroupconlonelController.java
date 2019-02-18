package com.fresh.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.IgnoreAuth;
import com.fresh.api.annotation.LoginUser;
import com.fresh.api.exception.CommonException;
import com.fresh.api.pojo.Group;
import com.fresh.api.pojo.Groupcolonel;
import com.fresh.api.service.IApiGroupService;
import com.fresh.api.service.IApiGroupconlonelService;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.api.util.ApiPageUtils;
import com.fresh.api.util.Util;
import com.fresh.manager.pojo.shop.User;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/groupcolonel")
public class ApiGroupconlonelController  extends ApiBaseAction {
	
	
	 @Autowired
	    private IApiGroupconlonelService apiGroupColonelService;

	    @Autowired
	    private IApiGroupService apiGroupService;
	    
	    /**
	     * 保存
	     */
	    @RequestMapping("/save")
	    public Object save(@LoginUser User loginUser,@RequestBody Groupcolonel groupcolonel) {
	    	apiGroupColonelService.save(loginUser,groupcolonel);
	        return toResponsSuccess(null);
	    }
	    
	    
	    @ApiOperation(value = "获取团长团购列表")
	    @RequestMapping(value = "/list")
	    public Object list(@LoginUser User loginUser,@RequestParam(value = "page", defaultValue =DEFAULT_PAGE) Integer page, @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
	    	Groupcolonel groupcolonel = new Groupcolonel();
	    	groupcolonel.setCreateUserId(loginUser.getId());
	        List<Groupcolonel> groupList = apiGroupColonelService.queryList(groupcolonel,page,size);
	        Date date  =  new Date();
	        for(Groupcolonel vo:groupList){
	        	boolean overdue = Util.dateCompare(date, vo.getGroupEndTime());
	        	vo.setOverdue(overdue);
	        }
	        int total = apiGroupColonelService.queryTotal(groupcolonel);

	        ApiPageUtils groupData = new ApiPageUtils(groupList, total, size, page);
	        return toResponsSuccess(groupData);
	    }
	    
	    @ApiOperation(value = "获取所有的开团列表")
	    @RequestMapping(value = "/allList")
	    public Object list(@RequestParam(value = "page", defaultValue =DEFAULT_PAGE) Integer page, @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
	    	Groupcolonel groupcolonel = new Groupcolonel();
	        List<Groupcolonel> groupList = apiGroupColonelService.queryList(groupcolonel,page,size);
	        Date date  =  new Date();
	        for(Groupcolonel vo:groupList){
	        	boolean overdue = Util.dateCompare(date, vo.getGroupEndTime());
	        	vo.setOverdue(overdue);
	        	Group voa = new Group();
				voa.setId(vo.getGroupId());
				Group group = apiGroupService.queryListWithGoodsByGroupId(voa);
				vo.setGroup(group);
	        }
	        int total = apiGroupColonelService.queryTotal(groupcolonel);

	        ApiPageUtils groupData = new ApiPageUtils(groupList, total, size, page);
	        return toResponsSuccess(groupData);
	    }
	    
	    
	    @ApiOperation(value = "获取团购列表单个")
	    @IgnoreAuth
	    @RequestMapping(value = "/query")
	    public Object queryListWithOtherInfo( int colonelGroupId) {
	    	
	    	Groupcolonel gcv = null;
			try {
				gcv = apiGroupColonelService.queryById(colonelGroupId);
				if(gcv == null)
					throw new CommonException("该团购已经不存在!");
				
				Date endTime = gcv.getGroupEndTime();
				int i = endTime.compareTo(new Date());
				if(i<0){
					gcv.setOverdue(true);
				}else{
					gcv.setOverdue(false);
				}
				
				Group vo = new Group();
				vo.setId(gcv.getGroupId());
				Group group = apiGroupService.queryListWithGoodsByGroupId(vo);
				gcv.setGroup(group);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return toResponsFail(e.getMessage());
			}
	        
	        return toResponsSuccess(gcv);
	    }
	
	
	
}
