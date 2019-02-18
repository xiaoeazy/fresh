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
import com.fresh.common.utils.TreeUtils;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.shop.Category;
import com.fresh.manager.shop.service.ICategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("category:list")
    public R list(Category category,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	  List<Category> userList = categoryService.queryList(category, page, limit);
          int total = (int) categoryService.queryTotal(category);
          PageUtils pageUtil = new PageUtils(userList, total, limit, page);
          return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("category:info")
    public R info(@PathVariable("id") Integer id) {
        Category category = categoryService.queryById(id);
        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("category:save")
    public R save(@RequestBody Category category) {
        categoryService.insertSelective(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("category:update")
    public R update(@RequestBody Category category) {
        categoryService.updateByPrimaryKeySelective(category);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("category:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        categoryService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Category category) {

        List<Category> list = categoryService.queryList(category,null,null);
        //添加顶级菜单
        Category root = new Category();
        root.setId(0);
        root.setName("一级分类");
        root.setParentId(-1);
        root.setOpen(true);
        root.setIsShow(true);
        list.add(0,root);
        return R.ok().put("list", list);
    }

    /**
     * 查看信息(全部加载页面渲染太慢！)
     */
    @RequestMapping("/getAreaTree")
    public R getAreaTree() {
        List<Category> list = categoryService.queryList(null,null,null);
        for (Category category : list) {
        	category.setValue(category.getId() + "");
        	category.setLabel(category.getName());
        }
        List<Category> node = TreeUtils.factorTree(list);
        return R.ok().put("node", node);
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("/getCategorySelect")
    public R getCategorySelect() {
    	Category c = new Category();
        c.setParentId(0);
        List<Category> list = categoryService.queryList(c,null,null);
        return R.ok().put("list", list);
    }
}
