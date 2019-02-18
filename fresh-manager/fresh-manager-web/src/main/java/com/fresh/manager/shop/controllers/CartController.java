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
import com.fresh.manager.pojo.shop.Cart;
import com.fresh.manager.shop.service.ICartService;



@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{
	@Autowired
	private ICartService cartService;
	

	
	/**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cart:list")
    public R list(Cart cart,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<Cart> addressList = cartService.queryList(cart, page, limit);
        int total = (int) cartService.queryTotal(cart);
        PageUtils pageUtil = new PageUtils(addressList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cart:info")
	public R info(@PathVariable("id") Integer id){
		Cart cart = cartService.queryById(id);
		return R.ok().put("cart", cart);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("cart:save")
	public R save(@RequestBody Cart cart){
		cartService.insertSelective(cart);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("cart:update")
	public R update(@RequestBody Cart cart){
		cartService.updateByPrimaryKeySelective(cart);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("cart:delete")
	public R delete(@RequestBody Integer[] ids){
		List<Integer> values = Arrays.asList(ids);
		cartService.deleteBatch(values);
		return R.ok();
	}
	
}
