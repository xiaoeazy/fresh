package com.fresh.manager.admin.controllers;

import java.util.ArrayList;
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
import com.fresh.common.utils.TreeUtils;
import com.fresh.manager.components.RegionCacheComponent;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.SysRegion;
import com.fresh.manager.service.ISysRegionService;

/**
 * 地址管理Controller
 *
 */
@RestController
@RequestMapping("sys/region")
public class SysRegionController extends BaseController {
    @Autowired
    private ISysRegionService sysRegionService;
    @Autowired
    private RegionCacheComponent regionCacheComponent;

    /**
     * 查看列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:region:list")
    public R list(SysRegion sysRegion,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<SysRegion> regionList = sysRegionService.queryList(sysRegion, page, limit);
        int total = sysRegionService.queryTotal(sysRegion);

        PageUtils pageUtil = new PageUtils(regionList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }

    /**
     * 根据主键获取信息
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:region:info")
    public R info(@PathVariable("id") Integer id) {
        SysRegion region = sysRegionService.queryById(id);

        return R.ok().put("region", region);
    }

    /**
     * 新增地址
     *
     * @param region 地址
     * @return R
     */
    @SysLog("新增地址")
    @RequestMapping("/save")
    @RequiresPermissions("sys:region:save")
    public R save(@RequestBody SysRegion region) {
        sysRegionService.insertSelective(region);

        return R.ok();
    }

    /**
     * 修改地址
     *
     * @param region 地址
     * @return R
     */
    @SysLog("修改地址")
    @RequestMapping("/update")
    @RequiresPermissions("sys:region:update")
    public R update(@RequestBody SysRegion region) {
        sysRegionService.updateByPrimaryKeySelective(region);

        return R.ok();
    }

    /**
     * 删除地址
     *
     * @param ids 主键集
     * @return R
     */
    @SysLog("删除地址")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:region:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        sysRegionService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查询所有国家
     *
     * @return R
     */
    @RequestMapping("/getAllCountry")
    public R getAllCountry() {
        List<SysRegion> list = regionCacheComponent.getAllCountry();
        return R.ok().put("list", list);
    }

    /**
     * 查询所有省份
     *
     * @return R
     */
    @RequestMapping("/getAllProvice")
    public R getAllProvice(@RequestParam(required = false) Integer areaId) {
        List<SysRegion> list = regionCacheComponent.getAllProviceByParentId(areaId);
        return R.ok().put("list", list);
    }

    /**
     * 查询所有城市
     *
     * @return R
     */
    @RequestMapping("/getAllCity")
    public R getAllCity(@RequestParam(required = false) Integer areaId) {
        List<SysRegion> list = regionCacheComponent.getChildrenCity(areaId);
        return R.ok().put("list", list);
    }


    /**
     * 查询所有区县
     *
     * @return R
     */
    @RequestMapping("/getChildrenDistrict")
    public R getChildrenDistrict(@RequestParam(required = false) Integer areaId) {
        List<SysRegion> list = regionCacheComponent.getChildrenDistrict(areaId);
        return R.ok().put("list", list);
    }

    /**
     * 查看信息(全部加载页面渲染太慢！)
     *
     * @return R
     */
    @RequestMapping("/getAreaTree")
    public R getAreaTree() {
        List<SysRegion> list = regionCacheComponent.load();
        for (SysRegion sysRegion : list) {
        	sysRegion.setValue(sysRegion.getId() + "");
            sysRegion.setLabel(sysRegion.getName());
        }
        List<SysRegion> node = TreeUtils.factorTree(list);

        return R.ok().put("node", node);
    }

    /**
     * 根据类型获取区域
     *
     * @param type 类型
     * @return R
     */
    @RequestMapping("/getAreaByType")
    public R getAreaByType(@RequestParam(required = false) Integer type) {

        List<SysRegion> list = new ArrayList<>();
        if (type==0) {

        } else if (type==1) {//省份
            list = regionCacheComponent.getAllCountry();
        } else if (type==2) {
            list = regionCacheComponent.getAllProvice();
        } else if (type==3) {
            list = regionCacheComponent.getAllCity();
        }
        return R.ok().put("list", list);
    }
}
