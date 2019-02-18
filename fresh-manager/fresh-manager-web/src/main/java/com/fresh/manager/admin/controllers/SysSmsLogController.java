package com.fresh.manager.admin.controllers;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fresh.common.annotation.SysLog;
import com.fresh.common.constant.Constant;
import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.SmsConfig;
import com.fresh.manager.pojo.SysSmsLog;
import com.fresh.manager.service.ISysConfigService;
import com.fresh.manager.service.ISysSmsLogService;

/**
 * 发送短信日志Controller
 *
 */
@RestController
@RequestMapping("/sys/smslog")
public class SysSmsLogController  extends BaseController {
    @Autowired
    private ISysSmsLogService smsLogService;
    @Autowired
    private ISysConfigService sysConfigService;
    /**
     * 短信配置KEY
     */
    private final static String KEY = Constant.SMS_CONFIG_KEY;

    /**
     * 所有日志列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:smslog:list")
    public R list(SysSmsLog sysSmsLog,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<SysSmsLog> smsLogList = smsLogService.queryList(sysSmsLog, page, limit);
        int total = (int) smsLogService.queryTotal(sysSmsLog);

        PageUtils pageUtil = new PageUtils(smsLogList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }

    /**
     * 根据主键获取日志信息
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:smslog:info")
    public R info(@PathVariable("id") String id) {
        SysSmsLog smsLog = smsLogService.queryById(id);
        return R.ok().put("smsLog", smsLog);
    }

    /**
     * 查看所有列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam SysSmsLog sysSmsLog) {

        List<SysSmsLog> list = smsLogService.queryList(sysSmsLog, null, null);

        return R.ok().put("list", list);
    }

    /**
     * 获取短信配置信息
     *
     * @return R
     */
    @RequestMapping("/config")
    public R config() {
        SmsConfig config = sysConfigService.getConfigObject(KEY, SmsConfig.class);

        return R.ok().put("config", config);
    }

    /**
     * 保存短信配置信息
     *
     * @param config 短信配置信息
     * @return R
     */
    @SysLog("保存短信配置信息")
    @RequestMapping("/saveConfig")
    public R saveConfig(@RequestBody SmsConfig config) {
        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
        return R.ok();
    }

    /**
     * 发送短信
     *
     * @param smsLog 短信
     * @return R
     */
    @RequestMapping("/sendSms")
    public R sendSms(@RequestBody SysSmsLog smsLog) {
        SysSmsLog SysSmsLog = smsLogService.sendSms(null,smsLog);
        return R.ok().put("result", SysSmsLog);
    }
}
