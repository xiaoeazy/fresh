package com.fresh.manager.shop.controllers;

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

import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.shop.Shipping;
import com.fresh.manager.pojo.shop.Shipping;
import com.fresh.manager.shop.service.IShippingService;


@RestController
@RequestMapping("/shipping")
public class ShippingController   extends BaseController{
    @Autowired
    private IShippingService shippingService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shipping:list")
    public R list(Shipping shipping,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Shipping> userList = shippingService.queryList(shipping, page, limit);
         int total = (int) shippingService.queryTotal(shipping);
         PageUtils pageUtil = new PageUtils(userList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shipping:info")
    public R info(@PathVariable("id") Integer id) {
        Shipping shipping = shippingService.queryById(id);
        return R.ok().put("shipping", shipping);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shipping:save")
    public R save(@RequestBody Shipping shipping) {
        shippingService.insertSelective(shipping);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shipping:update")
    public R update(@RequestBody Shipping shipping) {
        shippingService.updateByPrimaryKeySelective(shipping);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shipping:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        shippingService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Shipping shipping) {
        List<Shipping> list = shippingService.queryList(shipping,null,null);
        return R.ok().put("list", list);
    }
}
