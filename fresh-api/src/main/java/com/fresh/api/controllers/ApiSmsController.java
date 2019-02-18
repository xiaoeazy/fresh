package com.fresh.api.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.IgnoreAuth;
import com.fresh.api.annotation.LoginUser;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.common.utils.R;
import com.fresh.common.utils.RequestUtil;
import com.fresh.manager.pojo.SysSmsLog;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.service.ISysSmsLogService;

/**
 * 发送短信接口Controller
 */
@RestController
@RequestMapping("/api/sms")
public class ApiSmsController  extends ApiBaseAction {
    @Autowired
    private ISysSmsLogService sysSmsService;

    /**
     * 发送短信
     *
     * @param request request
     * @param params 请求参数{mobile：电话号码字符串，中间用英文逗号间隔,content：内容字符串,stime：追加发送时间，可为空，为空为及时发送}
     * @return R
     */
    @IgnoreAuth
    @RequestMapping("/smscode")
    public R sendSms(HttpServletRequest request,@LoginUser User loginUser, @RequestBody SysSmsLog syssmslog) {
        String validIP = RequestUtil.getIpAddrByRequest(request);
//        if (ResourceUtil.getConfigByName("sms.validIp").indexOf(validIP) < 0) {
//            throw new RRException("非法IP请求！");
//        }
//      smsLog.setMobile(params.get("mobile"));
//      smsLog.setContent(params.get("content"));
//      String stime = params.get("stime");
//      if (StringUtils.isNotEmpty(stime)) {
//          smsLog.setStime(DateUtils.convertStringToDate(stime));
//       }
        
        
        sysSmsService.sendSms(loginUser, syssmslog);
//        return R.ok().put("result", sysSmsLogEntity);
        return R.ok().put("result", syssmslog);
    }
}
