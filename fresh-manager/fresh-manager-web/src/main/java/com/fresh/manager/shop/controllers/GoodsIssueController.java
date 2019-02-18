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
import com.fresh.manager.pojo.shop.Goodsissue;
import com.fresh.manager.shop.service.IGoodsIssueService;


@RestController
@RequestMapping("/goodsissue")
public class GoodsIssueController  extends BaseController {
    @Autowired
    private IGoodsIssueService goodsIssueService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsissue:list")
    public R list(Goodsissue Goodsissue,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Goodsissue> userList = goodsIssueService.queryList(Goodsissue, page, limit);
         int total = (int) goodsIssueService.queryTotal(Goodsissue);
         PageUtils pageUtil = new PageUtils(userList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsissue:info")
    public R info(@PathVariable("id") Integer id) {
        Goodsissue goodsIssue = goodsIssueService.queryById(id);
        return R.ok().put("goodsIssue", goodsIssue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsissue:save")
    public R save(@RequestBody Goodsissue goodsIssue) {
        goodsIssueService.insertSelective(goodsIssue);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsissue:update")
    public R update(@RequestBody Goodsissue goodsIssue) {
        goodsIssueService.updateByPrimaryKeySelective(goodsIssue);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsissue:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        goodsIssueService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Goodsissue Goodsissue) {
        List<Goodsissue> list = goodsIssueService.queryList(Goodsissue,null,null);
        return R.ok().put("list", list);
    }
}
