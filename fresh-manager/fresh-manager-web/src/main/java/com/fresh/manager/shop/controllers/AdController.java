package com.fresh.manager.shop.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.shop.Ad;
import com.fresh.manager.shop.service.IAdService;


@RestController
@RequestMapping("/ad")
public class AdController extends BaseController {
    @Autowired
    private IAdService adService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ad:list")
    public R list(Ad ad,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<Ad> userList = adService.queryList(ad, page, limit);
        int total = (int) adService.queryTotal(ad);
        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ad:info")
    public R info(@PathVariable("id") Integer id) {
        Ad ad = adService.queryById(id);
        return R.ok().put("ad", ad);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ad:save")
    public R save(@RequestBody Ad ad) {
        adService.insertSelective(ad);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ad:update")
    public R update(@RequestBody Ad ad) {
        adService.updateByPrimaryKeySelective(ad);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ad:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        adService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Ad ad) {
        List<Ad> list = adService.queryList(ad,null,null);
        return R.ok().put("list", list);
    }
}
