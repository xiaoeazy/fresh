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
import com.fresh.manager.pojo.shop.Attributecategory;
import com.fresh.manager.shop.service.IAttributeCategoryService;


@RestController
@RequestMapping("/attributecategory")
public class AttributeCategoryController  extends BaseController{
    @Autowired
    private IAttributeCategoryService attributeCategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attributecategory:list")
    public R list(Attributecategory attributecategory,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Attributecategory> addressList = attributeCategoryService.queryList(attributecategory, page, limit);
         int total = (int) attributeCategoryService.queryTotal(attributecategory);
         PageUtils pageUtil = new PageUtils(addressList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attributecategory:info")
    public R info(@PathVariable("id") Integer id) {
    	Attributecategory attributeCategory = attributeCategoryService.queryById(id);
        return R.ok().put("attributeCategory", attributeCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attributecategory:save")
    public R save(@RequestBody Attributecategory attributeCategory) {
        attributeCategoryService.insertSelective(attributeCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attributecategory:update")
    public R update(@RequestBody Attributecategory attributeCategory) {
        attributeCategoryService.updateByPrimaryKeySelective(attributeCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attributecategory:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        attributeCategoryService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Attributecategory attributeCategory) {
        List<Attributecategory> list = attributeCategoryService.queryList(attributeCategory,null,null);
        return R.ok().put("list", list);
    }
}
