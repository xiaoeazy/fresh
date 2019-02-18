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
import com.fresh.manager.pojo.shop.Address;
import com.fresh.manager.shop.service.IAddressService;

@RestController
@RequestMapping("/address")
public class AddressController  extends BaseController{
    @Autowired
    private IAddressService addressService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("address:list")
    public R list(Address address,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<Address> addressList = addressService.queryList(address, page, limit);
        int total = (int) addressService.queryTotal(address);
        PageUtils pageUtil = new PageUtils(addressList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("address:info")
    public R info(@PathVariable("id") Integer id) {
        Address address = addressService.queryById(id);
        return R.ok().put("address", address);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("address:save")
    public R save(@RequestBody Address address) {
        addressService.insertSelective(address);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("address:update")
    public R update(@RequestBody Address address) {
        addressService.updateByPrimaryKeySelective(address);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("address:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        addressService.deleteBatch(values);
        return R.ok();
    }
}
