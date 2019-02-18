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
import com.fresh.manager.pojo.shop.Userlevel;
import com.fresh.manager.shop.service.IUserLevelService;


@RestController
@RequestMapping("/userlevel")
public class UserLevelController  extends BaseController {
    @Autowired
    private IUserLevelService userLevelService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("userlevel:list")
    public R list(Userlevel userlevel,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<Userlevel> userLevelList = userLevelService.queryList(userlevel, page, limit);
        int total = (int) userLevelService.queryTotal(userlevel);
        PageUtils pageUtil = new PageUtils(userLevelList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("userlevel:info")
    public R info(@PathVariable("id") Short id) {
        Userlevel userLevel = userLevelService.queryById(id);
        return R.ok().put("userLevel", userLevel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("userlevel:save")
    public R save(@RequestBody Userlevel userLevel) {
        userLevelService.insertSelective(userLevel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("userlevel:update")
    public R update(@RequestBody Userlevel userLevel) {
        userLevelService.updateByPrimaryKeySelective(userLevel);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("userlevel:delete")
    public R delete(@RequestBody Short[] ids) {
    	List<Short> values = Arrays.asList(ids);
        userLevelService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping("queryAll")
    public R queryAll(Userlevel userlevel) {
        List<Userlevel> userLevelList = userLevelService.queryList(userlevel,null,null);
        return R.ok().put("list", userLevelList);
    }
}
