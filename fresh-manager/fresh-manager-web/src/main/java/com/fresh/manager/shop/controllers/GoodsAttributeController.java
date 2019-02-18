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
import com.fresh.manager.pojo.shop.Goodsattribute;
import com.fresh.manager.shop.service.IGoodsAttributeService;



@RestController
@RequestMapping("/goodsattribute")
public class GoodsAttributeController  extends BaseController {
	@Autowired
	private IGoodsAttributeService goodsAttributeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goodsattribute:list")
	public R list(Goodsattribute goodsattribute,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit){
		 	List<Goodsattribute> userList = goodsAttributeService.queryList(goodsattribute, page, limit);
	        int total = (int) goodsAttributeService.queryTotal(goodsattribute);
	        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
	        return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goodsattribute:info")
	public R info(@PathVariable("id") Integer id){
		Goodsattribute goodsattribute = goodsAttributeService.queryById(id);
		
		return R.ok().put("goodsAttribute", goodsattribute);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goodsattribute:save")
	public R save(@RequestBody Goodsattribute goodsattribute){
		goodsAttributeService.insertSelective(goodsattribute);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goodsattribute:update")
	public R update(@RequestBody Goodsattribute goodsattribute){
		goodsAttributeService.updateByPrimaryKeySelective(goodsattribute);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goodsattribute:delete")
	public R delete(@RequestBody Integer[] ids){
		List<Integer> values = Arrays.asList(ids);
		goodsAttributeService.deleteBatch(values);
		return R.ok();
	}
	
}
