package com.fresh.api.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.IgnoreAuth;
import com.fresh.api.exception.CommonException;
import com.fresh.api.pojo.other.FullUserInfo;
import com.fresh.api.pojo.other.WxLoginInfo;
import com.fresh.api.pojo.other.WxLoginReturnInfo;
import com.fresh.api.service.IApiWeiXinService;
import com.fresh.api.util.ApiBaseAction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * API登录授权
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    @Qualifier("apiUserService")
    private IApiWeiXinService apiWeiXinService;

//    /**
//     * 登录
//     */
//    @IgnoreAuth
//    @PostMapping("login")
//    @ApiOperation(value = "登录接口")
//    public R login(String mobile, String password) {
//        Assert.isBlank(mobile, "手机号不能为空");
//        Assert.isBlank(password, "密码不能为空");
//
//        //用户登录
//        long userId = userService.login(mobile, password);
//
//        //生成token
//        Map<String, Object> map = tokenService.createToken(userId);
//
//        return R.ok(map);
//    }

    /**
     * 登录
     */
    @ApiOperation(value = "登录")
    @IgnoreAuth
    @PostMapping("/login_by_weixin")
    public Object loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo) {
        WxLoginReturnInfo uri = null;
		try {
			String code  = wxLoginInfo.getCode();;
	        FullUserInfo  fullUserInfo = wxLoginInfo.getUserInfo();
	        if (null == fullUserInfo) {
	        	 throw new  CommonException("登录失败");
	        }
			uri = apiWeiXinService.loginByWeixin(code, fullUserInfo, this.getClientIp());
			return toResponsSuccess(uri);
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return toResponsFail("登录失败");
		}
       
    }
}
