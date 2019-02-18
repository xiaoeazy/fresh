package com.fresh.manager.admin.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.common.annotation.SysLog;
import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.Query;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.SysMacro;
import com.fresh.manager.pojo.SysRegion;
import com.fresh.manager.service.ISysMacroService;

/**
 * 通用字典表Controller
 *
 */
@RestController
@RequestMapping("sys/macro")
public class SysMacroController extends BaseController {
    @Autowired
    private ISysMacroService sysMacroService;

    /**
     * 所有字典列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:macro:list")
    public R list(SysMacro sysMacro,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<SysMacro> sysMacroList = sysMacroService.queryList(sysMacro, page, limit);
        int total = (int) sysMacroService.queryTotal(sysMacro);
        PageUtils pageUtil = new PageUtils(sysMacroList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 根据主键获取字典信息
     *
     * @param macroId 主键
     * @return R
     */
    @RequestMapping("/info/{macroId}")
    @RequiresPermissions("sys:macro:info")
    public R info(@PathVariable("macroId") Long macroId) {
        SysMacro sysMacro = sysMacroService.queryById(macroId);
        return R.ok().put("macro", sysMacro);
    }

    /**
     * 新增字典
     *
     * @param sysMacro 字典
     * @return R
     */
    @SysLog("新增字典")
    @RequestMapping("/save")
    @RequiresPermissions("sys:macro:save")
    public R save(@RequestBody SysMacro sysMacro) {
        sysMacroService.insertSelective(sysMacro);

        return R.ok();
    }

    /**
     * 修改字典
     *
     * @param sysMacro 字典
     * @return R
     */
    @SysLog("修改字典")
    @RequestMapping("/update")
    @RequiresPermissions("sys:macro:update")
    public R update(@RequestBody SysMacro sysMacro) {
        sysMacroService.update(sysMacro);

        return R.ok();
    }

    /**
     * 删除字典
     *
     * @param macroIds 主键集
     * @return R
     */
    @SysLog("删除字典")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:macro:delete")
    public R delete(@RequestBody Long[] macroIds) {
    	List<Long> values = Arrays.asList(macroIds);
        sysMacroService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查看字典列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/queryAll")
    public R queryAll(SysMacro sysMacro) {

        List<SysMacro> list = sysMacroService.queryList(sysMacro, null, null);

        return R.ok().put("list", list);
    }

    /**
     * 根据value查询数据字典
     *
     * @param value value
     * @return R
     */
    @RequestMapping("/queryMacrosByValue")
    public R queryMacrosByValue(@RequestParam String value) {

        List<SysMacro> list = sysMacroService.queryMacrosByValue(value);

        return R.ok().put("list", list);
    }
}
