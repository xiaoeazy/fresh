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
import com.fresh.manager.pojo.shop.Product;
import com.fresh.manager.shop.service.IProductService;

/**
 * Controller
 */
@RestController
@RequestMapping("/product")
public class ProductController  extends BaseController {
    @Autowired
    private IProductService productService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:list")
    public R list(Product product,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	  List<Product> userList = productService.queryList(product, page, limit);
          int total = (int) productService.queryTotal(product);
          PageUtils pageUtil = new PageUtils(userList, total, limit, page);
          return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:info")
    public R info(@PathVariable("id") Integer id) {
        Product product = productService.queryById(id);
        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:save")
    public R save(@RequestBody Product product) {
        productService.insertSelective(product);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:update")
    public R update(@RequestBody Product product) {
        productService.updateByPrimaryKeySelective(product);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        productService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查看所有列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/queryAll")
    public R queryAll( Product product) {
        List<Product> list = productService.queryList(product,null,null);
        return R.ok().put("list", list);
    }

    /**
     * 根据goodsId查询商品
     *
     * @param goodsId
     * @return
     */
    @RequestMapping("/queryByGoodsId/{goodsId}")
    public R queryByGoodsId(@PathVariable("goodsId") Integer goodsId) {
        Product product = new Product();
        product.setGoodsId(goodsId);
        List<Product> list = productService.queryList(product,null,null);
        return R.ok().put("list", list);
    }
}
