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
import com.fresh.manager.pojo.shop.Goodsgallery;
import com.fresh.manager.shop.service.IGoodsGalleryService;


@RestController
@RequestMapping("/goodsgallery")
public class GoodsGalleryController extends BaseController {
    @Autowired
    private IGoodsGalleryService goodsGalleryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsgallery:list")
    public R list(Goodsgallery goodsgallery,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Goodsgallery> userList = goodsGalleryService.queryList(goodsgallery, page, limit);
         int total = (int) goodsGalleryService.queryTotal(goodsgallery);
         PageUtils pageUtil = new PageUtils(userList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsgallery:info")
    public R info(@PathVariable("id") Integer id) {
    	Goodsgallery goodsgallery = goodsGalleryService.queryById(id);

        return R.ok().put("goodsGallery", goodsgallery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsgallery:save")
    public R save(@RequestBody Goodsgallery goodsgallery) {
        goodsGalleryService.insertSelective(goodsgallery);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsgallery:update")
    public R update(@RequestBody Goodsgallery goodsgallery) {
        goodsGalleryService.updateByPrimaryKeySelective(goodsgallery);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsgallery:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        goodsGalleryService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll( Goodsgallery goodsgallery) {

        List<Goodsgallery> list = goodsGalleryService.queryList(goodsgallery,null,null);

        return R.ok().put("list", list);
    }
}
