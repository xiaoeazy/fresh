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
import com.fresh.manager.pojo.shop.Goodsspecification;
import com.fresh.manager.shop.service.IGoodsSpecificationService;

/**
 * 商品对应规格表值表Controller
 *
 */
@RestController
@RequestMapping("/goodsspecification")
public class GoodsSpecificationController  extends BaseController {
    @Autowired
    private IGoodsSpecificationService goodsSpecificationService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("Goodsspecification:list")
    public R list(Goodsspecification goodsspecification,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Goodsspecification> userList = goodsSpecificationService.queryList(goodsspecification, page, limit);
         int total = (int) goodsSpecificationService.queryTotal(goodsspecification);
         PageUtils pageUtil = new PageUtils(userList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("Goodsspecification:info")
    public R info(@PathVariable("id") Integer id) {
    	Goodsspecification goodsspecification = goodsSpecificationService.queryById(id);

        return R.ok().put("goodsSpecification", goodsspecification);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("Goodsspecification:save")
    public R save(@RequestBody Goodsspecification goodsspecification) {
        goodsSpecificationService.insertSelective(goodsspecification);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("Goodsspecification:update")
    public R update(@RequestBody Goodsspecification goodsspecification) {
        goodsSpecificationService.updateByPrimaryKeySelective(goodsspecification);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("Goodsspecification:delete")
    public R delete(@RequestBody Integer[]ids) {
    	List<Integer> values = Arrays.asList(ids);
        goodsSpecificationService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Goodsspecification goodsspecification) {

        List<Goodsspecification> list = goodsSpecificationService.queryList(goodsspecification,null,null);

        return R.ok().put("list", list);
    }
}
