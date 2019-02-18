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
import com.fresh.manager.pojo.shop.Specification;
import com.fresh.manager.shop.service.ISpecificationService;



@RestController
@RequestMapping("/specification")
public class SpecificationController   extends BaseController {
    @Autowired
    private ISpecificationService specificationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("specification:list")
    public R list(Specification specification,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	  List<Specification> userList = specificationService.queryList(specification, page, limit);
          int total = (int) specificationService.queryTotal(specification);
          PageUtils pageUtil = new PageUtils(userList, total, limit, page);
          return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("specification:info")
    public R info(@PathVariable("id") Integer id) {
        Specification specification = specificationService.queryById(id);
        return R.ok().put("specification", specification);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("specification:save")
    public R save(@RequestBody Specification specification) {
        specificationService.insertSelective(specification);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("specification:update")
    public R update(@RequestBody Specification specification) {
        specificationService.updateByPrimaryKeySelective(specification);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("specification:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        specificationService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Specification specification) {
        List<Specification> list = specificationService.queryList(specification,null,null);
        return R.ok().put("list", list);
    }
}
