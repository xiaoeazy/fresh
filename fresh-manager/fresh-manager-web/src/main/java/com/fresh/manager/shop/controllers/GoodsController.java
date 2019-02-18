package com.fresh.manager.shop.controllers;

import java.util.ArrayList;
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
import com.fresh.manager.pojo.shop.Goods;
import com.fresh.manager.shop.service.IGoodsService;


@RestController
@RequestMapping("/goods")
public class GoodsController  extends BaseController {
	@Autowired
	private IGoodsService goodsService;

	/**
	 * 查看列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goods:list")
	public R list(Goods goods,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
			goods.setIsDelete(false);
		  	List<Goods> userList = goodsService.queryList(goods, page, limit);
	        int total = (int) goodsService.queryTotal(goods);
	        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
	        return R.ok().put("page", pageUtil);
	}

	/**
	 * 查看不属于groupid的所有goods
	 * 
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listWhereNoInGroupByGroupId")
	@RequiresPermissions("goods:list")
	public R listWhereNoInGroupByGroupId(Integer groupId,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
		List<Goods> groupGoodsList = new ArrayList<Goods>();
		if (groupId == null) {
			groupGoodsList = goodsService.queryAll();
		} else {
			groupGoodsList = goodsService.listWhereNoInGroupByGroupId(groupId);
		}
		PageUtils pageUtil = new PageUtils(groupGoodsList, groupGoodsList.size(), limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 查看属于groupid的所有goods
	 * 
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listWhereInGroupByGroupId")
	@RequiresPermissions("goods:list")
	public R listWhereInGroupByGroupId(Integer groupId,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
		List<Goods> groupGoodsList = new ArrayList<Goods>();
		if (groupId == null) {
			groupGoodsList = new ArrayList<Goods>();

		} else {
			groupGoodsList = goodsService.listWhereInGroupByGroupId(groupId);
		}
		PageUtils pageUtil = new PageUtils(groupGoodsList, groupGoodsList.size(), limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 查看信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:info")
	public R info(@PathVariable("id") Integer id) {
		Goods goods = goodsService.queryById(id);

		return R.ok().put("goods", goods);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:save")
	public R save(@RequestBody Goods goods) {
		goodsService.insertSelective(goods);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:update")
	public R update(@RequestBody Goods goods) {
		goodsService.updateByPrimaryKeySelective(goods);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:delete")
	public R delete(@RequestBody Integer[] ids) {
		List<Integer> values = Arrays.asList(ids);
		goodsService.deleteBatch(values);
		return R.ok();
	}

	/**
	 * 查看所有列表
	 */
	@RequestMapping("/queryAll")
	public R queryAll(Goods goods) {
		goods.setIsDelete(false);
		List<Goods> list = goodsService.queryList(goods,null,null);

		return R.ok().put("list", list);
	}

	/**
	 * 商品回收站
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping("/historyList")
	public R historyList(Goods goods,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
		goods.setIsDelete(true);
		List<Goods> goodsList = goodsService.queryList(goods,page,limit);
		int total = (int) goodsService.queryTotal(goods);
		PageUtils pageUtil = new PageUtils(goodsList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 商品从回收站恢复
	 */
	@RequestMapping("/back")
	@RequiresPermissions("goods:back")
	public R back(@RequestBody Integer[] ids) {
		goodsService.back(ids);
		return R.ok();
	}

	/**
	 * 总计
	 */
	@RequestMapping("/queryTotal")
	public R queryTotal(Goods goods) {
		goods.setIsDelete(false);
		int sum = (int) goodsService.queryTotal(goods);
		return R.ok().put("goodsSum", sum);
	}

	/**
	 * 上架
	 */
	@RequestMapping("/enSale")
	public R enSale(@RequestBody Integer id) {
		goodsService.enSale(id);
		return R.ok();
	}

	/**
	 * 下架
	 */
	@RequestMapping("/unSale")
	public R unSale(@RequestBody Integer id) {
		goodsService.unSale(id);
		return R.ok();
	}
}
