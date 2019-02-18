package com.fresh.manager.admin.controllers;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.SysLog;
import com.fresh.manager.service.ISysLogService;

/**
 * 系统日志Controller
 *
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 系统日志列表
     *
     * @param params 请求参数
     * @return R
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(SysLog sysLog,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<SysLog> sysLogList = sysLogService.queryList(sysLog, page, limit);
        int total = (int) sysLogService.queryTotal(sysLog);

        PageUtils pageUtil = new PageUtils(sysLogList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }

}
