package com.fresh.api.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fresh.api.pojo.other.FullUserInfo;
import com.fresh.api.pojo.other.UserInfo;
import com.fresh.api.pojo.other.WxLoginReturnInfo;
import com.fresh.api.service.IApiWeiXinService;
import com.fresh.api.service.ITokenService;
import com.fresh.api.util.ApiUserUtils;
import com.fresh.api.util.CommonUtil;
import com.fresh.common.exception.RRException;
import com.fresh.common.utils.CharUtil;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IUserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApiWeiXinServiceImpl implements IApiWeiXinService {
	@Autowired
	private IUserService userService;
	@Autowired
	private ITokenService tokenService;
	
	@Override
	public WxLoginReturnInfo loginByWeixin(String code,FullUserInfo  fullUserInfo,String clientIp) {
			WxLoginReturnInfo wi = new WxLoginReturnInfo();
	        //
	        UserInfo userInfo = fullUserInfo.getUserInfo();

	        //获取openid
	        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
//	        logger.info("》》》组合token为：" + requestUrl);
	        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);

	        if (null == sessionData || StringUtils.isEmpty(sessionData.getString("openid"))) {
	            throw new RRException("登录失败");
	        }
	        //验证用户信息完整性
	        String sha1 = CommonUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
	        if (!fullUserInfo.getSignature().equals(sha1)) {
	            throw new  RRException("登录失败");
	        }
	        Date nowTime = new Date();
	        String openid = sessionData.getString("openid");
	        User userVo = userService.queryByOpenId(openid);
	        if (null == userVo) {
	        	userVo = new User();
	            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
	            userVo.setPassword(sessionData.getString("openid"));
	            userVo.setRegisterTime(nowTime);
	            userVo.setRegisterIp(clientIp);
	            userVo.setLastLoginIp(clientIp);
	            userVo.setLastLoginTime(nowTime);
	            userVo.setWeixinOpenid(openid);
	            userVo.setAvatar(userInfo.getAvatarUrl());
	            userVo.setGender((Short)userInfo.getGender()); // //性别 0：未知、1：男、2：女
	            userVo.setNickname(userInfo.getNickName());
	            userService.insertSelective(userVo);
	        } else {
	        	userVo.setLastLoginIp(clientIp);
	        	userVo.setLastLoginTime(nowTime);
	        	userService.updateByPrimaryKeySelective(userVo);
	        }

	        Map<String, Object> tokenMap = tokenService.createToken(userVo.getId());
	        String token = MapUtils.getString(tokenMap, "token");

	        if (null == userInfo || StringUtils.isEmpty(token)) {
	        	 throw new  RRException("登录失败");
	        }

	        wi.setToken(token);
	        wi.setUserInfo(userInfo);
	        wi.setUserId(userVo.getId());
	        return wi;
	}
}
