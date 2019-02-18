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
import com.fresh.manager.pojo.shop.Attribute;
import com.fresh.manager.shop.service.IAttributeService;


@RestController
@RequestMapping("/attribute")
public class AttributeController extends BaseController{
    @Autowired
    private IAttributeService attributeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attribute:list")
    public R list(Attribute attribute,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Attribute> addressList = attributeService.queryList(attribute, page, limit);
         int total = (int) attributeService.queryTotal(attribute);
         PageUtils pageUtil = new PageUtils(addressList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attribute:info")
    public R info(@PathVariable("id") Integer id) {
        Attribute attribute = attributeService.queryById(id);
        return R.ok().put("attribute", attribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attribute:save")
    public R save(@RequestBody Attribute attribute) {
        attributeService.insertSelective(attribute);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attribute:update")
    public R update(@RequestBody Attribute attribute) {
        attributeService.updateByPrimaryKeySelective(attribute);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attribute:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        attributeService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Attribute attribute) {
        List<Attribute> list = attributeService.queryList(attribute,null,null);
        return R.ok().put("list", list);
    }
}
