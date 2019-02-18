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
import com.fresh.manager.pojo.shop.Ordergoods;
import com.fresh.manager.shop.service.IOrderGoodsService;


@RestController
@RequestMapping("/ordergoods")
public class OrderGoodsController  extends BaseController{
	@Autowired
	private IOrderGoodsService orderGoodsService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ordergoods:list")
	public R list(Ordergoods ordergoods,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit){
		 List<Ordergoods> userList = orderGoodsService.queryList(ordergoods, page, limit);
	        int total = (int) orderGoodsService.queryTotal(ordergoods);
	        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
	        return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ordergoods:info")
	public R info(@PathVariable("id") Integer id){
		Ordergoods orderGoods = orderGoodsService.queryById(id);

		return R.ok().put("orderGoods", orderGoods);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ordergoods:save")
	public R save(@RequestBody Ordergoods ordergoods){
		orderGoodsService.insertSelective(ordergoods);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ordergoods:update")
	public R update(@RequestBody Ordergoods ordergoods){
		orderGoodsService.updateByPrimaryKeySelective(ordergoods);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ordergoods:delete")
	public R delete(@RequestBody Integer[] ids){
		List<Integer> values = Arrays.asList(ids);
		orderGoodsService.deleteBatch(values);

		return R.ok();
	}

	/**
	 * 查看所有列表
	 */
	@RequestMapping("/queryAll")
	public R queryAll(Ordergoods ordergoods) {
		List<Ordergoods> list = orderGoodsService.queryList(ordergoods,null,null);
		return R.ok().put("list", list);
	}
}
