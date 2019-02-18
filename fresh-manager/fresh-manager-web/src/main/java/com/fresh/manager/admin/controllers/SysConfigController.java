package com.fresh.manager.admin.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.common.annotation.SysLog;
import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.common.validator.ValidatorUtils;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.SysConfig;
import com.fresh.manager.service.ISysConfigService;

/**
 * 系统配置信息Controller
 *
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends BaseController {
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 所有配置列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R list(SysConfig sysConfig,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	
        List<SysConfig> configList = sysConfigService.queryList(sysConfig, page, limit);
        
        int total = (int) sysConfigService.queryTotal(sysConfig);
        PageUtils pageUtil = new PageUtils(configList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }


    /**
     * 根据主键获取配置信息
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public R info(@PathVariable("id") Long id) {
        SysConfig config = sysConfigService.queryById(id);
        return R.ok().put("config", config);
    }

    /**
     * 新增配置
     *
     * @param config 配置
     * @return R
     */
    @SysLog("新增配置")
    @RequestMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R save(@RequestBody SysConfig config) {
        ValidatorUtils.validateEntity(config);

        sysConfigService.insertSelective(config);

        return R.ok();
    }

    /**
     * 修改配置
     *
     * @param config 配置
     * @return R
     */
    @SysLog("修改配置")
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R update(@RequestBody SysConfig config) {
        ValidatorUtils.validateEntity(config);

        sysConfigService.updateByPrimaryKeySelective(config);

        return R.ok();
    }

    /**
     * 删除配置
     *
     * @param ids 主键集
     * @return R
     */
    @SysLog("删除配置")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public R delete(@RequestBody Long[] ids) {
    	List<Long> values = Arrays.asList(ids);
        sysConfigService.deleteBatch(values);
        return R.ok();
    }

}
