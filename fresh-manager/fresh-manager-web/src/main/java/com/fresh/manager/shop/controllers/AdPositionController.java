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
import com.fresh.manager.pojo.shop.Adposition;
import com.fresh.manager.shop.service.IAdPositionService;

@RestController
@RequestMapping("/adposition")
public class AdPositionController extends BaseController {
    @Autowired
    private IAdPositionService adpositionService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("Adposition:list")
    public R list(Adposition adposition,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<Adposition> userList = adpositionService.queryList(adposition, page, limit);
        int total = (int) adpositionService.queryTotal(adposition);
        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("Adposition:info")
    public R info(@PathVariable("id") Integer id) {
    	Adposition adposition = adpositionService.queryById(id);
        return R.ok().put("Adposition", adposition);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("Adposition:save")
    public R save(@RequestBody Adposition adposition) {
        adpositionService.insertSelective(adposition);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("Adposition:update")
    public R update(@RequestBody Adposition adposition) {
        adpositionService.updateByPrimaryKeySelective(adposition);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("Adposition:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        adpositionService.deleteBatch(values);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll( Adposition adposition) {
        List<Adposition> list = adpositionService.queryList(adposition,null,null);
        return R.ok().put("list", list);
    }
}
