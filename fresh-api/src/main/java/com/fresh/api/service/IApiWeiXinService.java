package com.fresh.api.service;

import com.fresh.api.pojo.other.FullUserInfo;
import com.fresh.api.pojo.other.WxLoginReturnInfo;

public interface IApiWeiXinService {

	WxLoginReturnInfo loginByWeixin(String code, FullUserInfo fullUserInfo, String clientIp);

    
    
}
