package com.fresh.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.LoginUser;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.manager.pojo.shop.Feedback;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IFeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class ApiFeedBackController  extends ApiBaseAction {
	@Autowired
	private IFeedbackService apiFeedBackService;
	 /**
     * 保存
     */
    @RequestMapping("/save")
    public Object save(@LoginUser User loginUser,@RequestBody Feedback feedback) {
    	try {
			apiFeedBackService.saveForApi(loginUser,feedback);
			return toResponsSuccess("感谢你的反馈");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return toResponsFail("反馈失败");
		}
    }
}
