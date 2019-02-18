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
import com.fresh.manager.pojo.shop.Keywords;
import com.fresh.manager.shop.service.IKeywordsService;


@RestController
@RequestMapping("/keywords")
public class KeywordsController  extends BaseController {
    @Autowired
    private IKeywordsService keywordsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("keywords:list")
    public R list(Keywords keywords,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Keywords> userList = keywordsService.queryList(keywords, page, limit);
         int total = (int) keywordsService.queryTotal(keywords);
         PageUtils pageUtil = new PageUtils(userList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("keywords:info")
    public R info(@PathVariable("id") Integer id) {
        Keywords keywords = keywordsService.queryById(id);

        return R.ok().put("keywords", keywords);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("keywords:save")
    public R save(@RequestBody Keywords keywords) {
        keywordsService.insertSelective(keywords);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("keywords:update")
    public R update(@RequestBody Keywords keywords) {
        keywordsService.updateByPrimaryKeySelective(keywords);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("keywords:delete")
    public R delete(@RequestBody Integer[]ids) {
    	List<Integer> values = Arrays.asList(ids);
        keywordsService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Keywords keywords) {
        List<Keywords> list = keywordsService.queryList(keywords, null, null);
        return R.ok().put("list", list);
    }
}
